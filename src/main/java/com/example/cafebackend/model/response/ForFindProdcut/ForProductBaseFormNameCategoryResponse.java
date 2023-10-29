package com.example.cafebackend.model.response.ForFindProdcut;


import lombok.Data;

import java.util.List;

import com.example.cafebackend.table.Category;

@Data
public class ForProductBaseFormNameCategoryResponse  {

    private String prodBaseId;

    private String prodTitleTh;

    private String prodTitleEng;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private List<String> formsName;

    private List<Category> category;

}
