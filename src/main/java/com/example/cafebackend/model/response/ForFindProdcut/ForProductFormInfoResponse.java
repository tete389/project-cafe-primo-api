package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.table.ProductBase;
import lombok.Data;

@Data
public class ForProductFormInfoResponse {

    private String prodFormId;

    private String prodForm;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double price;

    private String description;

    private ProductBase productBase;

    //private List<Boolean> materialUsedEnable;



}
