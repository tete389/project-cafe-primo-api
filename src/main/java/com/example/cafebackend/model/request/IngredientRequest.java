package com.example.cafebackend.model.request;


import lombok.Data;

@Data
public class IngredientRequest {

    private String prodId;

    private String mateId;

    private Double mateCount;

    private String mateUnit;

    private String description;

}
