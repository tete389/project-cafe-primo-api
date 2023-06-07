package com.example.cafebackend.model.response;

import com.example.cafebackend.table.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForIngredientResponse {

    private String mateId;

    private String prodId;

    private String prodName;

    private String mateName;

    private Boolean isEnable;

    private Double mateCount;

    private String mateUnit;

    private String description;

}
