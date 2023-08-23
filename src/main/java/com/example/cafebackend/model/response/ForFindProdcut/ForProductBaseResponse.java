package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.table.ProductForm;
import lombok.Data;

import java.util.List;

@Data
public class ForProductBaseResponse {

    private String prodBaseId;

    private String prodTitle;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private List<ProductForm> productForms;


}
