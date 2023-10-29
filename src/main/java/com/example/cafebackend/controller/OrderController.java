package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.mapper.OrderMapper;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.model.request.ProdRequest;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.model.response.ForOrder.ForRecentGroup;
import com.example.cafebackend.model.response.ForOrder.ForRecentMaterail;
import com.example.cafebackend.model.response.ForOrder.ForRecentOption;
import com.example.cafebackend.model.response.ForOrder.ForRecentProduct;
import com.example.cafebackend.model.response.ForOrder.OrderCollect;
import com.example.cafebackend.model.response.ForOrder.OrderResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

@AllArgsConstructor
@Service
public class OrderController {

    private OrderService orderService;

    private OrderDetailProductService orderDetailProductService;

    private OrderDetailMaterialService orderDetailMaterialService;

    private OrderDetailOptionService orderDetailOptionService;

    private OrderDetailProductController orderDetailProductController;

    private OrderDetailMaterialController orderDetailMaterialController;

    private MaterialService materialService;

    private SettingShopService shopService;

    private CustomerService customerService;

    private CustomerController customerController;

    private OrderDetailPointService orderDetailPointService;

    private OrderMapper orderMapper;

    private TokenService tokenService;

    ////////////////////////////////////////////////

    public MessageResponse createOrder(OrderRequest request) throws Exception {
        // String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        // String formattedDate =
        // currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Integer countOrderToDay = orderService.findCountByOrderToDay(currentDate);
        String no_Order = countOrderToDay < 9 ? "00" + (countOrderToDay + 1)
                : countOrderToDay > 99 ? String.valueOf((countOrderToDay + 1)) : "0" + (countOrderToDay + 1);
        /// create no. order and status customer name
        if (Objects.isNull(request.getCustomerName()) || request.getCustomerName().isEmpty()) {
            request.setCustomerName(EString.NONE.getValue());
        }
        if (Objects.isNull(request.getNote()) || request.getNote().isEmpty()) {
            request.setNote(EString.NONE.getValue());
        }
        Order order = orderService.createOrder(no_Order, EString.CREATING.getValue(), request.getCustomerName(),
                request.getNote());
        /// manage order
        Order manageOrder = manageOrder(order, request);
        /// update order
        Order resOrder = orderService.updateOrder(manageOrder);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Create Order success");
        res.setRes(resOrder);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateProdInOrder(OrderRequest request) throws Exception {
        Optional<Order> orderOpt = orderService.findById(request.getOrderId());
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty())
            throw OrderException.updateFailNotFound();
        Order order = orderOpt.get();
        /// clear old data and add status
        boolean clearODTProd = orderDetailProductController.clearOrderDetailProductInOrder(order);
        if (!clearODTProd)
            throw OrderException.updateFailDataNull();
        boolean clearODTMate = orderDetailMaterialController.clearProductRecordInOrder(order);
        if (!clearODTMate)
            throw OrderException.updateFailDataNull();
        boolean clearODTPoint = customerController.clearDetailPointInOrder(order);
        if (!clearODTPoint)
            throw OrderException.updateFailDataNull();
        ////
        Order oderEdit = orderService.clearOrder(order, EString.EDITING.getValue());
        /// manage order
        Order manageOrder = manageOrder(oderEdit, request);
        /// update order
        Order resOrder = orderService.updateOrder(manageOrder);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Update Order success");
        res.setRes(resOrder);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateOrderConfirm(String orderId, String statusAction, OrderCollect collectPoint,
            List<String> statusOrder)
            throws BaseException {
        tokenService.checkTokenEmp();
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty())
            throw OrderException.updateFailDataNull();
        Order order = orderOpt.get();
        statusOrder.add(order.getStatus());

        //// payment
        if (order.getStatus().equals(EString.PAYMENT.getValue())
                && (Objects.isNull(statusAction) || statusAction.isEmpty())) {
            /// set mate use
            for (OrderDetailMaterial orderDetailMaterial : order.getOrderDetailMaterials()) {
                Optional<Material> mateOpt = materialService.findById(orderDetailMaterial.getMateId());
                if (Objects.isNull(mateOpt) || mateOpt.isEmpty())
                    throw OrderException.updateFailNotFound();
                Material material = mateOpt.get();
                double newStock = material.getStock() - orderDetailMaterial.getAmountUsed();
                material.setStock(newStock);
                materialService.updateMaterial(material);
            }
            /// set spend point
            if (!order.getOrderDetailPoint().isEmpty()) {
                OrderDetailPoint oderOpt = order.getOrderDetailPoint().get(0);
                /// check phoneNumber
                Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(oderOpt.getPhoneNumber());
                if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
                    throw OrderException.findFail();
                Customer customer = customerOpt.get();
                /// check spend point
                double result = customer.getPointCount();
                if (oderOpt.getAction().equals(EString.SPEND_POINT.getValue())) {
                    result = result + oderOpt.getActionPoint();
                }
                // if (result <= 0) throw OrderException.CannotSpend();
                customer.setPointCount(result);
                customerService.updateCustomer(customer);
            }
            /// set status and update
            order.setStatus(EString.MAKING.getValue());
            // order.setResponsible(emp.getEmpName());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.MAKING.getValue());
            res.setRes(resOrder);
            return res;
        }

        ////
        if (order.getStatus().equals(EString.MAKING.getValue())
                && (Objects.isNull(statusAction) || statusAction.isEmpty())) {
            /// set status and update
            order.setStatus(EString.RECEIVE.getValue());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.RECEIVE.getValue());
            res.setRes(resOrder);
            return res;
        }

        if (order.getStatus().equals(EString.PAYMENT.getValue()) && statusAction.equals(EString.KEEP.getValue())
                && (Objects.isNull(statusAction) || statusAction.isEmpty())) {
            /// set status and update
            order.setStatus(EString.KEEP.getValue());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.KEEP.getValue());
            res.setRes(resOrder);
            return res;
        }

        ////
        if (statusAction.equals(EString.PAYMENT.getValue())) {
            ///
            if (!order.getOrderDetailMaterials().isEmpty()
                    && order.getStatus().equals(EString.MAKING.getValue())) {
                for (OrderDetailMaterial orderDetailMaterial : order.getOrderDetailMaterials()) {
                    Optional<Material> mateOpt = materialService.findById(orderDetailMaterial.getMateId());
                    if (Objects.isNull(mateOpt) || mateOpt.isEmpty())
                        throw OrderException.updateFailNotFound();
                    Material material = mateOpt.get();
                    double newStock = material.getStock() + orderDetailMaterial.getAmountUsed();
                    material.setStock(newStock);
                    materialService.updateMaterial(material);
                }
            }
            /// set status and update
            order.setStatus(EString.PAYMENT.getValue());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.PAYMENT.getValue());
            res.setRes(resOrder);
            return res;
        }

        //// payment to success
        if (order.getStatus().equals(EString.PAYMENT.getValue())
                && statusAction.equals(EString.SUCCESS.getValue())) {
            /// set mate use
            for (OrderDetailMaterial orderDetailMaterial : order.getOrderDetailMaterials()) {
                Optional<Material> mateOpt = materialService.findById(orderDetailMaterial.getMateId());
                if (Objects.isNull(mateOpt) || mateOpt.isEmpty())
                    throw OrderException.updateFailNotFound();
                Material material = mateOpt.get();
                double newStock = material.getStock() - orderDetailMaterial.getAmountUsed();
                material.setStock(newStock);
                materialService.updateMaterial(material);
            }
            /// set spend point
            if (!order.getOrderDetailPoint().isEmpty()) {
                OrderDetailPoint oderOpt = order.getOrderDetailPoint().get(0);
                /// check phoneNumber
                Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(oderOpt.getPhoneNumber());
                if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
                    throw OrderException.findFail();
                Customer customer = customerOpt.get();
                /// check spend point
                double result = customer.getPointCount();
                if (oderOpt.getAction().equals(EString.SPEND_POINT.getValue())) {
                    result = result + oderOpt.getActionPoint();
                }
                // if (result <= 0) throw OrderException.CannotSpend();
                customer.setPointCount(result);
                customerService.updateCustomer(customer);
            }
            if (!Objects.isNull(collectPoint)) {
                if ((collectPoint.getCollectPoint() != 0) && (!collectPoint.getPhoneNumber().isEmpty())) {
                    /// check phoneNumber if not have go crete customer
                    Customer customer = new Customer();
                    Optional<Customer> customerOpt = customerService
                            .findCustomerByPhoneNumber(collectPoint.getPhoneNumber());
                    if (Objects.isNull(customerOpt) || customerOpt.isEmpty()) {
                        customer = customerService.createCustomer(collectPoint.getPhoneNumber());
                    } else {
                        customer = customerOpt.get();
                    }
                    List<SettingShop> setting = shopService.findAll();
                    double bonus = order.getOrderPrice() * setting.get(0).getPointCollectRate();
                    double bonusMore = bonus + collectPoint.getCollectMore();
                    /// create point detail
                    OrderDetailPoint detailPoint = orderDetailPointService.createPointDetail(order,
                            collectPoint.getPhoneNumber(),
                            EString.COLLECT_POINT.getValue(), bonusMore);
                    order.getOrderDetailPoint().add(detailPoint);
                    /// update point
                    customer.setPointCount(customer.getPointCount() + bonusMore);
                    customerService.updateCustomer(customer);
                }
            }
            /// set status and update
            order.setStatus(EString.SUCCESS.getValue());
            // order.setResponsible(emp.getEmpName());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.SUCCESS.getValue());
            res.setRes(resOrder);
            return res;
        }

        //// to success order
        if (statusAction.equals(EString.SUCCESS.getValue())) {
            /// set collect point
            if (!Objects.isNull(collectPoint)) {
                if ((collectPoint.getCollectPoint() != 0) && (!collectPoint.getPhoneNumber().isEmpty())) {
                    /// check phoneNumber if not have go crete customer
                    Customer customer = new Customer();
                    Optional<Customer> customerOpt = customerService
                            .findCustomerByPhoneNumber(collectPoint.getPhoneNumber());
                    if (Objects.isNull(customerOpt) || customerOpt.isEmpty()) {
                        customer = customerService.createCustomer(collectPoint.getPhoneNumber());
                    } else {
                        customer = customerOpt.get();
                    }
                    List<SettingShop> setting = shopService.findAll();
                    double bonus = order.getOrderPrice() * setting.get(0).getPointCollectRate();
                    double bonusMore = bonus + collectPoint.getCollectMore();
                    /// create point detail
                    OrderDetailPoint detailPoint = orderDetailPointService.createPointDetail(order,
                            collectPoint.getPhoneNumber(),
                            EString.COLLECT_POINT.getValue(), bonusMore);
                    order.getOrderDetailPoint().add(detailPoint);
                    /// update point
                    customer.setPointCount(customer.getPointCount() + bonusMore);
                    customerService.updateCustomer(customer);
                }
            }
            /// set status and update
            order.setStatus(EString.SUCCESS.getValue());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.SUCCESS.getValue());
            res.setRes(resOrder);
            return res;
        }

        //// to cancel order
        if (statusAction.equals(EString.CANCEL.getValue())) {
            /// check spend point
            if (!order.getOrderDetailPoint().isEmpty()
                    && (order.getStatus().equals(EString.RECEIVE.getValue())
                            || order.getStatus().equals(EString.SUCCESS.getValue()))) {
                OrderDetailPoint oderOpt = order.getOrderDetailPoint().get(0);
                /// check phoneNumber
                Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(oderOpt.getPhoneNumber());
                if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
                    throw OrderException.findFail();
                Customer customer = customerOpt.get();
                /// check spend point
                double result = customer.getPointCount();
                if (oderOpt.getAction().equals(EString.SPEND_POINT.getValue())) {
                    result = result - oderOpt.getActionPoint();
                }
                customer.setPointCount(result);
                customerService.updateCustomer(customer);
            }
            /// set status and update
            order.setStatus(EString.CANCEL.getValue());
            Order resOrder = orderService.updateOrder(order);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage(EString.CANCEL.getValue());
            res.setRes(resOrder);
            return res;
        }

        /// set status and update
        order.setStatus(EString.EDITING.getValue());
        // order.setResponsible(emp.getEmpName());
        Order resOrder = orderService.updateOrder(order);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Order Edit");
        res.setRes(resOrder);
        return res;

    }
    ////////////////////////////////////////////////

    ////////////////////////////////////////////////

    public MessageResponse getOrderInfoById(String orderId, String detail) throws BaseException {
        /// validate
        if (Objects.isNull(orderId))
            throw OrderException.findFail();
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty())
            throw OrderException.findFail();
        Order order = orderOpt.get();

        if (!(Objects.isNull(detail) || detail.isEmpty()) && detail.equals("true")) {
            /// response
            MessageResponse res = new MessageResponse();
            OrderResponse orderRes = orderMapper.toOrderResponse(order);
            res.setMessage("Get Order detail ById ");
            res.setRes(orderRes);
            return res;
        }

        /// res not detail
        MessageResponse res = new MessageResponse();
        res.setMessage("Get Order ById ");
        res.setRes(order);
        return res;

    }

    ////////////////////////////////////////////////

    public MessageResponse getRecentOrder(String recentMaterial, String recentProduct, String recentOption,
            String dateStat, String dateEnd, String statusOrder, String income, Integer pageSize, Integer pageNum)
            throws BaseException {

        if (Objects.isNull(dateStat) || dateStat.isEmpty()) {
            throw OrderException.findFail();
        }
        if (Objects.isNull(dateEnd) || dateEnd.isEmpty()) {
            throw OrderException.findFail();
        }
        if (Objects.isNull(statusOrder) || statusOrder.isEmpty()) {
            throw OrderException.findFail();
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 100;
        }
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }

        /////
        if (!(Objects.isNull(recentProduct) || recentProduct.isEmpty())
                && !(Objects.isNull(recentMaterial) || recentMaterial.isEmpty())
                && !(Objects.isNull(recentOption) || recentOption.isEmpty())) {
            if (recentProduct.equals("true") && recentMaterial.equals("true") && recentOption.equals("true")) {
                ForRecentGroup recentGroup = new ForRecentGroup();
                List<ForRecentProduct> ListRecentProd = getRecentProduct(dateStat, dateEnd, statusOrder);
                recentGroup.setRecentProduct(ListRecentProd);
                List<ForRecentMaterail> ListRecentMate = getRecentMaterial(dateStat, dateEnd, statusOrder);
                recentGroup.setRecentMaterail(ListRecentMate);
                List<ForRecentOption> ListRecentOpt = getRecentOption(dateStat, dateEnd, statusOrder);
                recentGroup.setRecentOption(ListRecentOpt);

                MessageResponse res = new MessageResponse();
                res.setMessage("Get OrderDetailProduct By Date");
                res.setRes(recentGroup);
                return res;
            }
        }
        if (!(Objects.isNull(recentProduct) || recentProduct.isEmpty()) && recentProduct.equals("true")) {
            List<ForRecentProduct> ListRecentProd = getRecentProduct(dateStat, dateEnd, statusOrder);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage("Get OrderDetailProduct By Date");
            res.setRes(ListRecentProd);
            return res;
        }
        /////
        if (!(Objects.isNull(recentMaterial) || recentMaterial.isEmpty()) && recentMaterial.equals("true")) {
            List<ForRecentMaterail> ListRecentMate = getRecentMaterial(dateStat, dateEnd, statusOrder);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage("Get OrderDetailMaterial By Date");
            res.setRes(ListRecentMate);
            return res;
        }
        /////
        if (!(Objects.isNull(recentOption) || recentOption.isEmpty()) && recentOption.equals("true")) {
            List<ForRecentOption> ListRecentOpt = getRecentOption(dateStat, dateEnd, statusOrder);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage("Get OrderDetailOption By Date");
            res.setRes(ListRecentOpt);
            return res;
        }
        ////
        List<Order> ListOrder;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (statusOrder.equals(EString.All.getValue())) {
            ListOrder = orderService.findByOrderBetweenDate(dateStat, dateEnd, pageable);
        } else {
            ListOrder = orderService.findByOrderBetweenDateByStatus(dateStat, dateEnd, statusOrder, pageable);
        }
        if (!(Objects.isNull(income) || income.isEmpty()) && income.equals("true")) {
            ResIncomeOfOrder resIncome = new ResIncomeOfOrder();
            // LocalDate dateDay = LocalDate.parse(dateEnd,
            // DateTimeFormatter.ISO_LOCAL_DATE);
            // int dayToSubtract = dateDay.getDayOfMonth();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // LocalDate inputDate = LocalDate.parse(dateEnd, formatter);
            // LocalDate daysBefore = inputDate.minusDays(dayToSubtract - 1);
            LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Bangkok"));
            LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
            String formattedDate = firstDayOfMonth.format(formatter);
            Integer incomeOfMonth = orderService.findIncomeOfMonth(formattedDate, dateEnd);
            resIncome.setIncomeOfMonth(incomeOfMonth);
            LocalDate date = LocalDate.parse(dateEnd);
            int weekOfYear = date.get(WeekFields.ISO.weekOfWeekBasedYear());
            int yearNow = currentDate.getYear();
            LocalDate firstDayOfYear = LocalDate.of(yearNow, 1, 1);
            LocalDate firstDayOfWeek = firstDayOfYear.with(WeekFields.ISO.weekOfYear(), weekOfYear);
            LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);
            Integer incomeOfWeek = orderService.findIncomeOfWeek(firstDayOfWeek.toString(), lastDayOfWeek.toString());
            resIncome.setIncomeOfWeek(incomeOfWeek);
            resIncome.setListOrder(ListOrder);
            MessageResponse res = new MessageResponse();
            res.setMessage("Get Order By Date");
            res.setRes(resIncome);
            return res;
        }
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Get Order By Date");
        res.setRes(ListOrder);
        return res;

    }

    @Data
    private static class ResIncomeOfOrder {
        private Integer incomeOfMonth;
        private Integer incomeOfWeek;
        private List<Order> ListOrder;
    }
    ////////////////////////////////////////////////

    public String countOrderNotPayment() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        // String formattedDate =
        // currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Integer count = orderService.findCountByOrderToDayStatus(EString.PAYMENT.getValue(), currentDate);
        return count.toString();
    }
    ////////////////////////////////////////////////

    public MessageResponse deleteOrder(String orderId) throws BaseException {
        tokenService.checkTokenEmp();
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty())
            throw OrderException.updateFailDataNull();
        orderService.deleteOder(orderId);
        MessageResponse res = new MessageResponse();
        res.setMessage("Delete Order success");
        return res;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public List<ForRecentProduct> getRecentProduct(String dateStat, String dateEnd, String statusOrder) {
        Set<String> ListODTProduct = new HashSet<>();
        if (statusOrder.equals(EString.All.getValue())) {
            ListODTProduct = orderDetailProductService.findOrderDetailProductNameThBetweenDate(dateStat,
                    dateEnd);
        } else {
            ListODTProduct = orderDetailProductService.findByOrderDetailProductNameThBetweenDateStatus(dateStat,
                    dateEnd, statusOrder);
        }
        List<ForRecentProduct> ListRecentProd = new ArrayList<>();
        ListODTProduct.forEach(nameTh -> {
            ForRecentProduct recentProd = new ForRecentProduct();
            recentProd.setProdNameTh(nameTh);
            if (statusOrder.equals(EString.All.getValue())) {
                recentProd.setQuantity(orderDetailProductService
                        .findByOrderDetailProductCountBetweenDate(dateStat, dateEnd, nameTh));
            } else {
                recentProd.setQuantity(orderDetailProductService
                        .findByOrderDetailProductCountBetweenDateStatus(dateStat, dateEnd, statusOrder, nameTh));
            }
            ListRecentProd.add(recentProd);
        });
        return ListRecentProd;
    }

    public List<ForRecentMaterail> getRecentMaterial(String dateStat, String dateEnd, String statusOrder) {
        Set<String> ListODTMaterial = new HashSet<>();
        if (statusOrder.equals(EString.All.getValue())) {
            ListODTMaterial = orderDetailMaterialService.findByOrderDetailMaterialNameBetweenDate(dateStat,
                    dateEnd);
        } else {
            ListODTMaterial = orderDetailMaterialService.findByOrderDetailMaterialNameBetweenDateStatus(dateStat,
                    dateEnd, statusOrder);
        }
        List<ForRecentMaterail> ListRecentMate = new ArrayList<>();
        ListODTMaterial.forEach(mate -> {
            ForRecentMaterail recentMate = new ForRecentMaterail();
            recentMate.setMaterailName(mate);

            if (statusOrder.equals(EString.All.getValue())) {
                recentMate.setQuantity(orderDetailMaterialService
                        .findByOrderDetailMaterialCountBetweenDate(dateStat, dateEnd, mate));
            } else {
                recentMate.setQuantity(orderDetailMaterialService
                        .findByOrderDetailMaterialCountBetweenDateStatus(dateStat, dateEnd, statusOrder, mate));
            }
            ListRecentMate.add(recentMate);
        });
        return ListRecentMate;
    }

    public List<ForRecentOption> getRecentOption(String dateStat, String dateEnd, String statusOrder) {
        Set<String> ListODTOption = new HashSet<>();
        if (statusOrder.equals(EString.All.getValue())) {
            ListODTOption = orderDetailOptionService.findByOrderDetailOptionNameThBetweenDate(dateStat,
                    dateEnd);
        } else {
            ListODTOption = orderDetailOptionService.findByOrderDetailOptionNameThBetweenDateStatus(dateStat,
                    dateEnd, statusOrder);
        }

        List<ForRecentOption> ListRecentOpt = new ArrayList<>();
        ListODTOption.forEach(opt -> {
            ForRecentOption recentOpt = new ForRecentOption();
            recentOpt.setOptionlNameTh(opt);
            if (statusOrder.equals(EString.All.getValue())) {
                recentOpt.setQuantity(orderDetailOptionService
                        .findByOrderDetailOptionCountBetweenDate(dateStat, dateEnd, opt));
            } else {
                recentOpt.setQuantity(orderDetailOptionService
                        .findByOrderDetailOptionCountBetweenDateStatus(dateStat, dateEnd, statusOrder, opt));
            }

            ListRecentOpt.add(recentOpt);
        });
        return ListRecentOpt;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Order manageOrder(Order order, OrderRequest request) throws Exception {
        /// total price
        double totalDetailPrices = 0;
        /// total discount
        double discount = 0;
        /// check spend point
        if (!Objects.isNull(request.getDiscount())) {
            if (!(Objects.isNull(request.getDiscount().getSpendPoint())
                    && Objects.isNull(request.getDiscount().getPhoneNumber()))) {
                if (request.getDiscount().getSpendPoint() != 0) {
                    /// check phoneNumber
                    Optional<Customer> customerOpt = customerService
                            .findCustomerByPhoneNumber(request.getDiscount().getPhoneNumber());
                    if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
                        throw OrderException.findFail();
                    Customer customer = customerOpt.get();
                    /// check spend point
                    Double spendPoint = Double.valueOf(request.getDiscount().getSpendPoint());
                    if ((customer.getPointCount() - spendPoint) <= 0)
                        throw OrderException.CannotSpend();
                    /// create point detail
                    OrderDetailPoint detailPoint = orderDetailPointService.createPointDetail(order,
                            customer.getPhoneNumber(), EString.SPEND_POINT.getValue(), (spendPoint * -1));
                    order.getOrderDetailPoint().add(detailPoint);
                    List<SettingShop> setting = shopService.findAll();
                    discount = spendPoint / setting.get(0).getPointSpendRate();
                }
            }

        }
        /// List mate Used
        List<OrderDetailMaterial> listODTMate = new ArrayList<>();
        List<OrderDetailMaterial> keepMateUse = new ArrayList<>();
        /// check prod request
        for (ProdRequest prodRequest : request.getProdRequests()) {
            /// create odt prod
            OrderDetailProduct orderDetailProduct = orderDetailProductController.createOrderDetailProduct(order,
                    prodRequest, keepMateUse);
            /// keep odt material
            listODTMate.addAll(keepMateUse);
            keepMateUse.clear();
            /// check prod price
            totalDetailPrices = totalDetailPrices + orderDetailProduct.getDetailPrice();
            /// add odt prod
            order.getOrderDetailProducts().add(orderDetailProduct);
        }
        /// check id material
        HashSet<String> listMateId = new HashSet<>();
        for (OrderDetailMaterial mate : listODTMate) {
            listMateId.add(mate.getMateId());
        }
        /// check material use
        for (String mateId : listMateId) {
            /// create odt mate
            OrderDetailMaterial orderDetailMaterial = orderDetailMaterialController.createOrderDetailMaterial(order,
                    mateId, listODTMate);
            /// add odt mate
            order.getOrderDetailMaterials().add(orderDetailMaterial);
        }

        /// add data
        order.setTotalDetailPrice(totalDetailPrices);
        order.setDiscount(discount);

        order.setStatus(EString.PAYMENT.getValue());
        order.setOrderPrice(totalDetailPrices - discount);
        return order;
    }

}
