package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.OrderRepository;
import com.example.cafebackend.table.Order;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    ////////////////////////////////////////////////////

    public Order createOrder(String noOrder, String status) {
        /// set Order id
//        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
//        String[] arrOfStr = timeStamp.split(" ", 5);
//        String[] arrOfStrDate = arrOfStr[0].split("-",5);
//        String ordId = "ORD"+arrOfStrDate[0]+arrOfStrDate[1]+noOrder+arrOfStrDate[2];
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ORD"+uuid.substring(0, 14);
        /// save
        Order table = new Order();
        table.setOrderId(uuid);
        table.setOrderNumber(noOrder);
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

    public List<Order> findByOrderBetweenDate(String start, String end) {
        ///
        return orderRepository.findOrderBetweenDate(start, end);
    }
    /////////////////////////

    public List<Order> findByOrderBetweenDateByStatus(String start, String end, String status) {
        ///
        return orderRepository.findOrderBetweenDateByStats(start, end, status);
    }
    /////////////////////////

    public Integer findCountByOrderToDay(String status) {
        ///
        return orderRepository.findCountByOrderToDay(status);
    }
    /////////////////////////

    public List<Order> findByOrderToDay() {
        ///
        return orderRepository.findByOrderToDay();
    }
    /////////////////////////

    public Order clearOrder(Order order, String status) throws OrderException {
        /// verify
        if(Objects.isNull(order)) throw OrderException.updateFailNotFound();
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
