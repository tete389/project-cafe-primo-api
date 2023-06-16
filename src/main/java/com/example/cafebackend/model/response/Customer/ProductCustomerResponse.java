package com.example.cafebackend.model.response.Customer;

import com.example.cafebackend.table.ProductBase;
import lombok.Data;

@Data
public class ProductCustomerResponse {
    private String prodId;

    private String prodImg;

    private Boolean isEnable;

    private Double point;

    private Double price;

    private String des;

    private ProductBase productBase;

    private Boolean isEnableMaterial;

}
