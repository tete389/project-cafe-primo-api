package com.example.cafebackend.service;

import com.example.cafebackend.model.request.OrderDetailRequest;
import com.example.cafebackend.repository.OrderDetailRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }


    //////////////////////////

    public Optional<OrderDetail> findById(Integer id){
        return orderDetailRepository.findById(id);
    }

    /////////////////////////

    public List<OrderDetail> findAllAddDetail() {
        return orderDetailRepository.findAll();
    }


    /////////////////////////

    public OrderDetail createOrderDetail(Order order) {
        OrderDetail table = new OrderDetail();
        table.setOrder(order);
        return orderDetailRepository.save(table);
    }

    /////////////////////////

    public OrderDetail updateOrderDetail(OrderDetail orderDetail, Product product, Type type, double prices, Integer amount) {
        orderDetail.setProduct(product);
        orderDetail.setType(type);
        orderDetail.setProdPriceS(prices);
        orderDetail.setProdAmount(amount);
        return orderDetailRepository.save(orderDetail);
    }

    /////////////////////////

    public void deleteOrderDetail(Integer orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }

    /////////////////////////


}
