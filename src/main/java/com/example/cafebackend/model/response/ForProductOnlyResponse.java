package com.example.cafebackend.model.response;//package com.example.cafebackend.model.response;


import lombok.Data;

@Data
public class ForProductOnlyResponse {

    private String prodId;

    private String prodForm;

    private String prodName;

    private String image;

    private Boolean isEnable;

    private Boolean isForSale;

    private Double price;

    private Double bonusPoint;

    private String description;

    private String baseProdId;


}
