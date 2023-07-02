package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.model.response.ForFindNecessary.CategoryNec;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialEnableNec;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateEnableAndCateInPFResponse {

    private String prodFormId;

    private String prodForm;

//    private String image;

    private Boolean isEnable;

    private Double price;

    private Double bonusPoint;

    private String description;

    private ProductBase productBase;

    private List<MaterialEnableNec> materialUsed;

    private List<CategoryNec> category;


}
