package com.example.cafebackend.model.response.ForFindMateUsed;

import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateUseInProdFormResponse {

    private String prodFormId;

    private String prodForm;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double price;

    private String description;

    private ProductBase productBase;

    private List<MaterialUsedNec>  materialInfoUsed;

}
