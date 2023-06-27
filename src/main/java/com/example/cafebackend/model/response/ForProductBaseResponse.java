package com.example.cafebackend.model.response;


import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import lombok.Data;

import java.util.List;

@Data
public class ForProductBaseResponse {

    private String prodBaseId;

    private String prodTitle;

    private Boolean isEnable;

    private String description;

    private List<ProductForm> productForms;


}
