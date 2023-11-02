package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.model.response.ForOrder.OrderCollect;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerController {

    private CustomerService customerService;

    // private OrderDetailPointController orderDetailPointController;

    private OrderDetailPointService orderDetailPointService;

    private OrderService orderService;

    private SettingShopService shopService;

    ////////////////////////////////////////////////

    public MessageResponse collectPoints(String orderId, OrderCollect orderCollect) throws BaseException {
        /// validate

        if (Objects.isNull(orderId) || orderId.isEmpty())
            throw OrderException.findFail();

        if (Objects.isNull(orderCollect.getPhoneNumber()) || orderCollect.getPhoneNumber().isEmpty())
            throw OrderException.findFail();
        // if(Objects.isNull(point) || point.isEmpty()) throw OrderException.findFail();

        /// check order
        Optional<Order> orderOpt = orderService.findById(orderId);
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty())
            throw OrderException.findFail();
        Order order = orderOpt.get();
        /// check order payment success ?
        if (!(order.getStatus().equals(EString.SUCCESS.getValue())
                || order.getStatus().equals(EString.RECEIVE.getValue()))) {
            throw OrderException.unpaid();
        }
        /// check phoneNumber if not have go crete customer
        Customer customer = new Customer();
        if (!customerService.existsByPhoneNumber(orderCollect.getPhoneNumber())) {
            customer = customerService.createCustomer(orderCollect.getPhoneNumber());
        } else {
            Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(orderCollect.getPhoneNumber());
            if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
                throw OrderException.findFail();
            customer = customerOpt.get();
        }

        List<SettingShop> setting = shopService.findAll();
        double bonus = order.getOrderPrice() * setting.get(0).getPointCollectRate();
        /// create point detail
        OrderDetailPoint detailPoint = orderDetailPointService.createPointDetail(order, orderCollect.getPhoneNumber(),
                EString.COLLECT_POINT.getValue(), bonus);
        order.getOrderDetailPoint().add(detailPoint);
        orderService.updateOrder(order);
        /// update point
        customer.setPointCount(customer.getPointCount() + bonus);
        Customer resCus = customerService.updateCustomer(customer);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("collectPoints Success");
        res.setRes(resCus);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse spendPoint(String phoneNumber, Double point) throws BaseException {
        /// validate
        if (Objects.isNull(phoneNumber) || phoneNumber.isEmpty())
            throw OrderException.findFail();
        if (Objects.isNull(point))
            throw OrderException.findFail();
        /// check phoneNumber
        Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(phoneNumber);
        if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
            throw OrderException.findFail();
        Customer customer = customerOpt.get();
        /// check spend point
        Double spendPoint = Double.valueOf(point);
        double result = customer.getPointCount() - spendPoint;
        if (result < 0)
            throw OrderException.CannotSpend();
        /// create point detail
        // OrderDetailPoint detailPoint =
        /// orderDetailPointService.createPointDetail(customer, Double.valueOf(point),
        /// EString.SPEND_POINT.getValue());
        /// update point
        customer.setPointCount(result);
        Customer resCus = customerService.updateCustomer(customer);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("spendPoint Success");
        res.setRes(resCus);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse getPoint(String phoneNumber) throws BaseException {
        /// validate
        if (Objects.isNull(phoneNumber) || phoneNumber.isEmpty())
            throw OrderException.findFail();
        /// check phoneNumber
        Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(phoneNumber);
        if (Objects.isNull(customerOpt) || customerOpt.isEmpty())
            throw OrderException.findFail();
        Double point = customerOpt.get().getPointCount();
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Point : " + point);
        res.setRes(point);
        return res;
    }
    ////////////////////////////////////////////////

    public boolean clearDetailPointInOrder(Order order) throws BaseException {
        /// validate
        List<OrderDetailPoint> orderDetailPoint = order.getOrderDetailPoint();
        if (orderDetailPoint.isEmpty())
            return true;
        ///
        List<String> OdtPointIdList = new ArrayList<>();
        for (OrderDetailPoint detailPoint : orderDetailPoint) {
            OdtPointIdList.add(detailPoint.getOdtPointId());
        }
        for (String OdtPoint : OdtPointIdList) {
            orderDetailPointService.deletePointDetail(OdtPoint);
            Optional<OrderDetailPoint> detailMaterial = orderDetailPointService.findById(OdtPoint);
            if (!(Objects.isNull(detailMaterial) || detailMaterial.isEmpty()))
                return false;
        }
        return true;
    }
}
