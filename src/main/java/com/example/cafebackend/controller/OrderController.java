package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.mapper.OrderMapper;
import com.example.cafebackend.mapper.ProductRecordMapper;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.model.request.ProdRequest;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.model.response.OrderResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    private OrderMapper orderMapper;

    private ProductRecordMapper productRecordMapper;

    private TokenService tokenService;

    ////////////////////////////////////////////////

    public MessageResponse createOrder(OrderRequest request) throws Exception {
        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Integer countOrderToDay = orderService.findCountByOrderToDay();
        String no_Order = countOrderToDay < 9 ? "00" + (countOrderToDay + 1) : countOrderToDay > 99 ? String.valueOf((countOrderToDay + 1)) : "0" + (countOrderToDay + 1);
        /// create no. order and status
        Order order = orderService.createOrder(no_Order, EString.CREATING.getValue());
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
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()) throw OrderException.updateFailNotFound();
        Order order = orderOpt.get();
        /// clear old data and add status
        boolean clearODTProd = orderDetailProductController.clearOrderDetailProductInOrder(order);
        if(!clearODTProd) throw OrderException.updateFailDataNull();
        boolean clearODTMate = orderDetailMaterialController.clearProductRecordInOrder(order);
        if(!clearODTMate) throw OrderException.updateFailDataNull();
        Order oderEdit =  orderService.clearOrder(order, EString.EDITING.getValue());
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

    public MessageResponse updateOrderConfirmPayment(String orderId) throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()) throw OrderException.updateFailDataNull();
        Order order =  orderOpt.get();
        /// set mate use
        for (OrderDetailMaterial orderDetailMaterial : order.getOrderDetailMaterials()) {
            Optional<Material> mateOpt = materialService.findById(orderDetailMaterial.getMateId());
            if (Objects.isNull(mateOpt) || mateOpt.isEmpty()) throw OrderException.updateFailNotFound();
            Material material = mateOpt.get();
            double newStock = material.getStock() - orderDetailMaterial.getAmountUsed();
            material.setStock(newStock);
            materialService.updateMaterial(material);
        }
        /// set status and update
        order.setStatus(EString.PAYMENT_SUCCESS.getValue());
        order.setResponsible(emp.getEmpName());
        Order resOrder =  orderService.updateOrder(order);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Payment Confirm success");
        res.setRes(resOrder);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateCancelOrder(String orderId) throws BaseException {
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()) throw OrderException.updateFailDataNull();
        Order order =  orderOpt.get();
        /// set status and update
        order.setStatus(EString.CANCEL_ORDER.getValue());
        Order resOrder =  orderService.updateOrder(order);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Cancel Order success");
        res.setRes(resOrder);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse getOrderById(String orderId) throws BaseException {
        /// validate
        if (Objects.isNull(orderId)) throw OrderException.findFail();
        Optional<Order> orderOpt = orderService.findById(orderId);
        /// res
        MessageResponse res = new MessageResponse();
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()){
            res.setMessage("Order is Null");
            return res;
        }
        Order order = orderOpt.get();
        res.setMessage("Get Order ById success");
        res.setRes(order);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse getOrderInfoById(String orderId) throws BaseException {
        /// validate
        if (Objects.isNull(orderId)) throw OrderException.findFail();
        Optional<Order> orderOpt = orderService.findById(orderId);

        /// response
        MessageResponse res = new MessageResponse();
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()){
            res.setMessage("Order is Null");
            return res;
        }
        Order order = orderOpt.get();
        OrderResponse orderRes = orderMapper.toOrderResponse(order);
        res.setMessage("Get OrderInfo ById success");
        res.setRes(orderRes);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse getOrderAll(){
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get All Order success");
        res.setRes(orderService.findAllOrder());
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse getOrderToDay(){
        /// check order today
        List<Order> orders =  orderService.findByOrderToDay();
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get Order today");
        res.setRes(orders);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse deleteOrder(String orderId) throws BaseException {
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()) throw OrderException.updateFailDataNull();
        orderService.deleteOder(orderId);
        MessageResponse res = new MessageResponse();
        res.setMessage("Delete Order success");
        return res;
    }


    public MessageResponse getRecentOrder(String dateStat, String dateEnd, String statusOrder) throws BaseException {

        if (Objects.isNull(dateStat) || dateStat.isEmpty()) throw OrderException.findFail();
        if (Objects.isNull(dateEnd) || dateEnd.isEmpty()) throw OrderException.findFail();
        if (Objects.isNull(statusOrder) || statusOrder.isEmpty()) throw OrderException.findFail();
        List<Order> ListOrder;
        if(statusOrder.equals(EString.ALL.getValue())) {
            ListOrder = orderService.findByOrderBetweenDate(dateStat, dateEnd);
        }
        else {
            ListOrder = orderService.findByOrderBetweenDateByStatus(dateStat, dateEnd, statusOrder);
        }
        /// response
        MessageResponse res = new MessageResponse();
        if (Objects.isNull(ListOrder) || ListOrder.isEmpty()){
            res.setMessage("Order is Null");
            return res;
        }
        res.setMessage("Get Order By Date");
        res.setRes(ListOrder);
        return res;
    }

    public MessageResponse getRecentOrderDetailProduct(String dateStat, String dateEnd) throws BaseException {

        if (Objects.isNull(dateStat) || dateStat.isEmpty()) throw OrderException.findFail();
        if (Objects.isNull(dateEnd) || dateEnd.isEmpty()) throw OrderException.findFail();

        List<OrderDetailProduct> ListODTProduct = orderDetailProductService.findByOrderDetailProductBetweenDate(dateStat, dateEnd, EString.PAYMENT_SUCCESS.getValue());

        /// response
        MessageResponse res = new MessageResponse();
        if (Objects.isNull(ListODTProduct) || ListODTProduct.isEmpty()){
            res.setMessage("OrderDetailProduct is Null");
            return res;
        }
        res.setMessage("Get OrderDetailProduct By Date");
        res.setRes(ListODTProduct);
        return res;
    }

    public MessageResponse getRecentOrderDetailMaterial(String dateStat, String dateEnd) throws BaseException {

        if (Objects.isNull(dateStat) || dateStat.isEmpty()) throw OrderException.findFail();
        if (Objects.isNull(dateEnd) || dateEnd.isEmpty()) throw OrderException.findFail();

        List<OrderDetailMaterial> ListODTMaterial = orderDetailMaterialService.findByOrderDetailMaterialBetweenDate(dateStat, dateEnd, EString.PAYMENT_SUCCESS.getValue());

        /// response
        MessageResponse res = new MessageResponse();
        if (Objects.isNull(ListODTMaterial) || ListODTMaterial.isEmpty()){
            res.setMessage("OrderDetailMaterial is Null");
            return res;
        }
        res.setMessage("Get OrderDetailMaterial By Date");
        res.setRes(ListODTMaterial);
        return res;
    }

    public MessageResponse getRecentOrderDetailOption(String dateStat, String dateEnd) throws BaseException {

        if (Objects.isNull(dateStat) || dateStat.isEmpty()) throw OrderException.findFail();
        if (Objects.isNull(dateEnd) || dateEnd.isEmpty()) throw OrderException.findFail();

        List<OrderDetailOption> ListODTOption = orderDetailOptionService.findByOrderDetailOptionBetweenDate(dateStat, dateEnd, EString.PAYMENT_SUCCESS.getValue());

        /// response
        MessageResponse res = new MessageResponse();
        if (Objects.isNull(ListODTOption) || ListODTOption.isEmpty()){
            res.setMessage("OrderDetailOption is Null");
            return res;
        }
        res.setMessage("Get OrderDetailOption By Date");
        res.setRes(ListODTOption);
        return res;
    }


    public Order manageOrder(Order order, OrderRequest request) throws Exception {
        /// total price
        double totalPrices = 0;
        /// total point
        double totalBonusPoint = 0;
        /// List mate Used
        List<OrderDetailMaterial> listODTMate = new ArrayList<>();
        List<OrderDetailMaterial> keepMateUse = new ArrayList<>();
        /// check prod request
        for(ProdRequest prodRequest : request.getProdRequests()){
            /// create odt prod
            OrderDetailProduct orderDetailProduct = orderDetailProductController.createOrderDetailProduct(order, prodRequest, keepMateUse);
            /// keep odt material
            listODTMate.addAll(keepMateUse);
            keepMateUse.clear();
            /// check prod price
            totalPrices = totalPrices + orderDetailProduct.getDetailPrice();
            /// check prod bonus point
            totalBonusPoint = totalBonusPoint + orderDetailProduct.getDetailPrice();
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
            OrderDetailMaterial orderDetailMaterial = orderDetailMaterialController.createOrderDetailMaterial(order, mateId, listODTMate);
            /// add odt mate
            order.getOrderDetailMaterials().add(orderDetailMaterial);
        }
        /// add data
        order.setTotalPrice(totalPrices);
        order.setTotalBonusPoint(totalBonusPoint);
        order.setNote(request.getNote());
        order.setStatus(EString.WAIT_PAYMENT.getValue());
        return order;
    }


}
