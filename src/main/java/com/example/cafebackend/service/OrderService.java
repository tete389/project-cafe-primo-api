package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.OrderRepository;
import com.example.cafebackend.table.Order;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    //////////////////////////

    public Optional<Order> findById(String id){
        ///
        return orderRepository.findById(id);
    }
    /////////////////////////

    public List<Order> findAllOrder() {
        ///
        return orderRepository.findAll();
    }
    /////////////////////////

    public String findByOrderCreateDateAsc() {
        ///
        return orderRepository.findByOrderCreateDateAsc();
    }
    /////////////////////////

    public Integer findByOrderToDay(String timeStamp) {
        return orderRepository.findByOrderToDay(timeStamp);
    }
    /////////////////////////

    public Order createOrder(String noOrder, String status) {
        /// set Order id
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
        String[] arrOfStr = timeStamp.split(" ", 5);
        String[] arrOfStrDate = arrOfStr[0].split("-",5);
        String ordId = "ORD"+arrOfStrDate[0]+arrOfStrDate[1]+noOrder+arrOfStrDate[2];
        /// save
        Order table = new Order();
        table.setOrderId(ordId);
        table.setNumber(noOrder);
        table.setStatus(status);
        return orderRepository.save(table);
    }
    /////////////////////////

    public Order updateOrder(Order order) throws BaseException {
        /// verify
        if(Objects.isNull(order)) throw OrderException.updateFailDataNull();
        /// save
        return orderRepository.save(order);
    }
    /////////////////////////

    public Order clearOrder(Order order, String status) throws OrderException {
        /// verify
        if(Objects.isNull(order)) throw OrderException.updateFailNotFound();
        order.setStatus(status);
        order.setTotalPrice(0.0);
        order.setTotalBonusPoint(0.0);
        order.getProductRecords().clear();
        /// save
        return orderRepository.save(order);
    }
    /////////////////////////

    public void deleteOder(String cateId) {
        ///
        orderRepository.deleteById(cateId);
    }
    /////////////////////////


}
