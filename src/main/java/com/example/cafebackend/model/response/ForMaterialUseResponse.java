package com.example.cafebackend.model.response;

import com.example.cafebackend.table.Option;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import lombok.Data;

@Data
public class ForMaterialUseResponse {

    private Double amountUsed;

    private ProductForm productForms;

    private ProductBase productBases;

    private Option options;
}
