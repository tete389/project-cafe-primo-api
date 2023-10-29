package com.example.cafebackend.model.response.ForFindProdcut;


import lombok.Data;

import java.util.List;

@Data
public class ForProductBaseMinPriceFormNameResponse {

    private String prodBaseId;

    private String prodTitleTh;

    private String prodTitleEng;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private Double productMinPrice;

    private List<String> formsName;


}
