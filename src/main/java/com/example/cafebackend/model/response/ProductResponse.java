package com.example.cafebackend.model.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Integer prodId;

    private String prodName;

    private String prodImg;

    private String prodStatus;

    private Double prodPrice;

    private String prodTimeProcess;
}
