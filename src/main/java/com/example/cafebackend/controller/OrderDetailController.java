package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.AddDetailRequest;
import com.example.cafebackend.model.request.OrderDetailRequest;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.service.OrderDetailService;
import com.example.cafebackend.service.OrderService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.service.TypeService;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderDetailController {

    private OrderService orderService;

    private OrderDetailService orderDetailService;

    private ProductService productService;

    private TypeService typeService;

    private AdditionalDetailController additionalDetailController;

    ////////////////////////////////////////////////

    public List<OrderDetail> getAllOrder(){
        return orderDetailService.findAllAddDetail();
    }

    ////////////////////////////////////////////////

    public Optional<OrderDetail> getByOrderId(Integer id){
        return orderDetailService.findById(id);
    }

    ////////////////////////////////////////////////

    public OrderDetail createOrderDetail(Order order, OrderDetailRequest request){
        Optional<Product> product = productService.findById(request.getProdId());
        if(product.isEmpty()){
            /// TODO
            return null;
        }

        Optional<Type> type = typeService.findById(request.getTypeId());
        if(type.isEmpty()){
            /// TODO
            return null;
        }

        //// TODO Sweetness

        Product prod = product.get();
        Type type1 = type.get();
        double prices = prod.getProdPrice() + type1.getTypePrice();

        OrderDetail orderDetail = orderDetailService.createOrderDetail(order);
        for(AddDetailRequest addDReq : request.getAddDetailRequestList()){
            AdditionalDetail addD = additionalDetailController.createAdditionalDetail(orderDetail, addDReq);
            //addList.add(addD);
            prices = prices + addD.getAddPriceS();
        }
        Integer amount =  request.getProdAmount();
        prices = prices * amount;

        return orderDetailService.updateOrderDetail(orderDetail, prod, type1, prices, amount);

    }
}
