package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.Category;
import lombok.Data;

import java.util.List;

@Data
public class ForProductBaseCateMateUseResponse {

    private String prodBaseId;

    private String prodTitleTh;

    private String prodTitleEng;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private List<MaterialUsedNec> materialUse;

    private List<Category> category;


}
