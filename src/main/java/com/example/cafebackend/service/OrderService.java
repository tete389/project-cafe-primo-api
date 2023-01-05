package com.example.cafebackend.service;

import com.example.cafebackend.repository.OrderRepository;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.AdditionalDetail;
import com.example.cafebackend.table.Order;
import com.example.cafebackend.table.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    //////////////////////////

    public Optional<Order> findById(Integer id){
        return orderRepository.findById(id);
    }

    /////////////////////////

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    /////////////////////////

    public Order createOrder() {
        Order table = new Order();
        table.setOrderStatus("doing");
        return orderRepository.save(table);
    }

    /////////////////////////

    public Order updateOrder(Order order, String status, double TotalPrice) {
        order.setOrderStatus(status);
        order.setOrderTotalPrice(TotalPrice);
        return orderRepository.save(order);
    }

    /////////////////////////

    public void deleteOder(Integer cateId) {
        orderRepository.deleteById(cateId);
    }

    /////////////////////////


}
