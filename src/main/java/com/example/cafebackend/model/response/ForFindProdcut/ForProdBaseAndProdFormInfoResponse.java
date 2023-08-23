package com.example.cafebackend.model.response.ForFindProdcut;


import lombok.Data;

import java.util.List;

@Data
public class ForProdBaseAndProdFormInfoResponse {

    private String prodBaseId;

    private String prodTitle;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private List<ForProductFormInfoAddOnResponse> productFormList;


}
