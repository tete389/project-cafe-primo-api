package com.example.cafebackend.model.response.ForFindProdcut;


import lombok.Data;


@Data
public class ForProductBaseMinPriceResponse {

    private String prodBaseId;

    private String prodTitle;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private Double productMinPrice;


}
