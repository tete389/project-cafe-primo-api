package com.example.cafebackend.model.response;//package com.example.cafebackend.model.response;


import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForProdAndListAddOnResponse {

    private String prodFormId;

    private String prodForm;

    private Boolean isEnable;

    private Double price;

    private Double bonusPoint;

    private String description;

    private ProductBase productBase;

    private List<AddOn> addOn = new ArrayList<>();

}
