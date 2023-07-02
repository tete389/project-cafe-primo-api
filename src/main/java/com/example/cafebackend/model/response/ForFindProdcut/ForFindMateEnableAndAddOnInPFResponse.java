package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialEnableNec;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateEnableAndAddOnInPFResponse {

    private String prodFormId;

    private String prodForm;

    private Boolean isEnable;

    private Double price;

    private Double bonusPoint;

    private String description;

    private List<MaterialEnableNec> materialUsed;

    private List<AddOn> addOn;

}
