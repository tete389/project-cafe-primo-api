package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.table.ProductForm;
import lombok.Data;

import java.util.List;

@Data
public class ForProductBaseMinPriceFormResponse {

    private String prodBaseId;

    private String prodTitle;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private Double productMinPrice;

    private List<ProductForm> productForms;


}
