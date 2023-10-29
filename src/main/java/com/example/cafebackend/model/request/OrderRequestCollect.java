package com.example.cafebackend.model.request;


import lombok.Data;

import com.example.cafebackend.model.response.ForOrder.OrderCollect;

@Data
public class OrderRequestCollect {

    private String orderId;

    private String status;

    private OrderCollect collectPoint;

}
