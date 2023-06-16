package com.example.cafebackend.model.response;//package com.example.cafebackend.model.response;


import com.example.cafebackend.table.ProductBase;
import lombok.Data;

@Data
public class ForProductOnlyResponse {

    private String prodFormId;

    private String prodForm;

    private String image;

    private Boolean isEnable;

    private Double price;

    private Double bonusPoint;

    private String description;

    private ProductBase productBase;


}
