package com.example.cafebackend.model.request;

import lombok.Data;

@Data
public class ManagePointsRequest {

    private String phoneNumber;

    private String status;

    private String point;

    private String orderId;
}
