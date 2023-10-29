package com.example.cafebackend.model.response.ForFindAddOnOpion;

import lombok.Data;


@Data
public class ForOptionResponse {

    private String optionId;

    private String optionNameTh;

    private String optionNameEng;

    private Double price;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String addOnId;


}
