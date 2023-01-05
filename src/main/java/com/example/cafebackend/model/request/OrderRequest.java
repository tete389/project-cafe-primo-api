package com.example.cafebackend.model.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private List<OrderDetailRequest> orderDetailRequestList;
}
