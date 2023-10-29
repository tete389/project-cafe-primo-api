package com.example.cafebackend.model.response.ForFindProdcut;

import com.example.cafebackend.table.AddOn;
import lombok.Data;

import java.util.List;

@Data
public class ForProductFormAddOnResponse {

    private String prodFormId;

    private String prodFormTh;

    private String prodFormEng;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double price;

    private String description;

    private List<AddOn> addOn;

}
