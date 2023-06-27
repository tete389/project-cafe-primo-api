package com.example.cafebackend.model.response.ForFind;

import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.MaterialUsed;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForFindMateUseInProdFormResponse {

    private String prodFormId;

    private String prodForm;

    private Boolean isEnable;

    private Double price;

    private Double bonusPoint;

    private String description;

    private ProductBase productBase;

    private List<MaterialUsedNec>  materialInfoUsed;

}
