package com.example.cafebackend.service;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.OrderRepository;
import com.example.cafebackend.table.Order;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    ////////////////////////////////////////////////////

    public Order createOrder(String noOrder, String status, String cutomerName, String note) {
        /// set Order id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ORD" + uuid.substring(0, 14);
        /// save
        Order table = new Order();
        table.setOrderId(uuid);
        table.setOrderNumber(noOrder);
        table.setStatus(status);
        table.setCustomerName(cutomerName);
        table.setNote(note);
        return orderRepository.save(table);
    }
    /////////////////////////

    public Order updateOrder(Order order) throws BaseException {
        /// verify
        if (Objects.isNull(order))
            throw OrderException.updateFailDataNull();
        /// save
        return orderRepository.save(order);
    }
    /////////////////////////

    public Optional<Order> findById(String id) {
        ///
        return orderRepository.findById(id);
    }
    /////////////////////////

    public List<Order> findAllOrder() {
        ///
        return orderRepository.findAll();
    }
    /////////////////////////

    public List<Order> findByOrderBetweenDate(String start, String end, Pageable pageable) {
        ///
        return orderRepository.findOrderBetweenDate(start, end, pageable).getContent();
    }
    /////////////////////////

    public List<Order> findByOrderBetweenDateByStatus(String start, String end, String status, Pageable pageable) {
        ///
        return orderRepository.findOrderBetweenDateByStats(start, end, status, pageable).getContent();
    }
    /////////////////////////

    public Integer findCountByOrderToDay(LocalDate date) {
        ///
        return orderRepository.findCountByOrderToDay(date);
    }
    /////////////////////////

    public Integer findCountByOrderToDayStatus(String status, LocalDate date) {
        ///
        return orderRepository.findCountByOrderToDayStatus(status, date);
    }
    /////////////////////////

    public List<Order> findByOrderToDay(LocalDate date) {
        ///
        return orderRepository.findByOrderToDay(date);
    }
    /////////////////////////

    public Integer findIncomeOfMonth(String start, String end) {
        ///
        return orderRepository.findIncomeOfMonth(start, end, EString.SUCCESS.getValue());
    }
    /////////////////////////

    public Integer findIncomeOfWeek(String start, String end) {
        ///
        return orderRepository.findIncomeOfWeek(start, end, EString.SUCCESS.getValue());
    }
    /////////////////////////

    public Order clearOrder(Order order, String status) throws OrderException {
        /// verify
        if (Objects.isNull(order))
            throw OrderException.updateFailNotFound();
        order.setStatus(status);
        order.setTotalDetailPrice(0.0);
        order.setOrderPrice(0.0);
        order.setDiscount(0.0);
        order.getOrderDetailProducts().clear();
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
