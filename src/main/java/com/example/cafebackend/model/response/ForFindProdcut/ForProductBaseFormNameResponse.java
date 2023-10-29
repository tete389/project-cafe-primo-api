package com.example.cafebackend.model.response.ForFindProdcut;


import lombok.Data;

import java.util.List;

@Data
public class ForProductBaseFormNameResponse {

    private String prodBaseId;

    private String prodTitleTh;

    private String prodTitleEng;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private List<String> formsName;


}
