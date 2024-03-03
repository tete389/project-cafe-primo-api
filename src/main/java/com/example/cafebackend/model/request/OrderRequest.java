package com.example.cafebackend.model.request;

import lombok.Data;

import java.util.List;

import com.example.cafebackend.model.response.ForOrder.OrderCollect;
import com.example.cafebackend.model.response.ForOrder.OrderDiscount;

@Data
public class OrderRequest {

    private String orderId;

    private List<ProdRequest> prodRequests;

    private String note;

    private OrderDiscount discount;

    private OrderCollect collect;

    private String customerName; 

}

