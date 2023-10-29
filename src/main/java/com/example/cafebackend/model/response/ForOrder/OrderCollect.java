package com.example.cafebackend.model.response.ForOrder;

import lombok.Data;

@Data
public class OrderCollect {

    private String phoneNumber;

    private Double collectPoint;

    private Double collectMore;

}
