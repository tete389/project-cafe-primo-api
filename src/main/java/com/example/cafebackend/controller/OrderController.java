package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.OrderDetailRequest;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.service.OrderDetailService;
import com.example.cafebackend.service.OrderService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.table.Order;
import com.example.cafebackend.table.OrderDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderController {

    private OrderService orderService;

    private OrderDetailService orderDetailService;

    private ProductService productService;

    private OrderDetailController orderDetailController;

    ////////////////////////////////////////////////

    public List<Order> getAllOrder(){
        return orderService.findAllOrder();
    }

    ////////////////////////////////////////////////

    public Optional<Order> getOrderById(Integer id){
        return orderService.findById(id);
    }

    ////////////////////////////////////////////////

    public Order createOrder(OrderRequest request){
        Order order = orderService.createOrder();

        double totalPrices = 0;
        for(OrderDetailRequest ordDReq : request.getOrderDetailRequestList()){
            OrderDetail ordD = orderDetailController.createOrderDetail(order, ordDReq);
            totalPrices = totalPrices + ordD.getProdPriceS();

        }
        return orderService.updateOrder(order, order.getOrderStatus(), totalPrices);
    }
}
