package com.example.cafebackend.model.response.ForFindAddOnOpion;

import lombok.Data;

import java.util.List;

@Data
public class ForOptionInfoResponse {

    private String optionId;

    private String optionNameTh;

    private String optionNameEng;

    private Double price;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private List<Boolean> materialUsedEnable;


}
