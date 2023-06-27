package com.example.cafebackend.controller;


import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.model.response.ApiResponse;
import com.example.cafebackend.model.response.EmployeeResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.Customer;
import com.example.cafebackend.table.Employee;
import com.example.cafebackend.table.Order;
import com.example.cafebackend.table.PointDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@AllArgsConstructor
@Service
public class CustomerController {

    private CustomerService customerService;

    //private OrderDetailPointController orderDetailPointController;

    private PointDetailService pointDetailService;

    private OrderService orderService;

    private TokenService tokenService;

    ////////////////////////////////////////////////

    public MessageResponse collectPoints(String phoneNumber, String point, String orderId) throws BaseException {
        /// validate
        if(Objects.isNull(phoneNumber) ||  phoneNumber.isEmpty()) throw OrderException.findFail();
        if(Objects.isNull(orderId) ||  orderId.isEmpty()) throw OrderException.findFail();
        if(Objects.isNull(point) ||  point.isEmpty()) throw OrderException.findFail();
        /// check order
        Optional<Order> orderOpt = orderService.findById(orderId);
        if(Objects.isNull(orderOpt) || orderOpt.isEmpty()) throw OrderException.findFail();
        Order order = orderOpt.get();
        /// check order payment success ?
        if(order.getStatus().equals(EString.PAYMENT_SUCCESS.getValue()) ) throw OrderException.unpaid();
        /// check phoneNumber if not have go crete customer
        Customer customer;
        if(!customerService.existsByPhoneNumber(phoneNumber)) {
            customer = customerService.createCustomer(phoneNumber);
        } else {
            Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(phoneNumber);
            if(Objects.isNull(customerOpt) || customerOpt.isEmpty()) throw OrderException.findFail();
            customer = customerOpt.get();
        }
        /// create  point detail
        PointDetail detailPoint = pointDetailService.createPointDetail(customer, order, Double.valueOf(point), EString.COLLECT_POINT.getValue());
        /// update point
        customer.setPointCount(Double.valueOf(customer.getPointCount() + point));
        Customer resCus =  customerService.updateCustomer(customer);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("collectPoints Success");
        res.setRes(resCus);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse spendPoint(String phoneNumber, String point) throws BaseException {
        /// validate
        if(Objects.isNull(phoneNumber) ||  phoneNumber.isEmpty()) throw OrderException.findFail();
        if(Objects.isNull(point) ||  point.isEmpty()) throw OrderException.findFail();
        /// check phoneNumber
        Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(phoneNumber);
        if(Objects.isNull(customerOpt) || customerOpt.isEmpty()) throw OrderException.findFail();
        Customer customer = customerOpt.get();
        /// check spend point
        Double spendPoint = Double.valueOf(point);
        double result =  customer.getPointCount() - spendPoint;
        if (result <= 0) throw OrderException.CannotSpend();
        /// create  point detail
        PointDetail detailPoint = pointDetailService.createPointDetail(customer, null, Double.valueOf(point), EString.SPEND_POINT.getValue());
        /// update point
        customer.setPointCount(result);
        Customer resCus =  customerService.updateCustomer(customer);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("spendPoint Success");
        res.setRes(resCus);
        return res;
    }
    ////////////////////////////////////////////////


    public MessageResponse getPoint(String phoneNumber) throws BaseException {
        /// validate
        if(Objects.isNull(phoneNumber) ||  phoneNumber.isEmpty()) throw OrderException.findFail();
        /// check phoneNumber
        Optional<Customer> customerOpt = customerService.findCustomerByPhoneNumber(phoneNumber);
        if(Objects.isNull(customerOpt) || customerOpt.isEmpty()) throw OrderException.findFail();
        Double point = customerOpt.get().getPointCount();
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("Point : "+point);
        res.setRes(point);
        return res;
    }
    ////////////////////////////////////////////////



}
