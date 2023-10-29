package com.example.cafebackend.model.response.ForFindAddOnOpion;

import lombok.Data;

import java.util.List;

@Data
public class ForAddOnOptionInfoResponse {

    private String dddOnId;

    private String addOnTitleTh;

    private String addOnTitleEng;

    private Boolean isManyOptions;

    private Boolean isEnable;

    private String description;

    private List<ForOptionInfoResponse> optionsInfo;

}
