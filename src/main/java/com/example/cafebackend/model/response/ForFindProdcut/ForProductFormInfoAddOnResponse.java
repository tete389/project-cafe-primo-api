package com.example.cafebackend.model.response.ForFindProdcut;

import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForProductFormInfoAddOnResponse {

    private String prodFormId;

    private String prodFormTh;

    private String prodFormEng;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double price;

    private String description;

    private ProductBase productBase;

    private List<AddOn> addOn;

}
