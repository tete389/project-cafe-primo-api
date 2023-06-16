package com.example.cafebackend.model.response;//package com.example.cafebackend.model.response;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForProdAndListIngResponse {

    private String prodId;

    private String prodForm;

    private String prodName;

    private Boolean isEnable;

    private Boolean isForSale;

    private Double price;

    private Double bonusPoint;

    private String description;

    //private List<Ingredient> ingredients = new ArrayList<>();

}
