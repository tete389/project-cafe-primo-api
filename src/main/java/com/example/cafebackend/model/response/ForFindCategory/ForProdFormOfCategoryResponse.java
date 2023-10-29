package com.example.cafebackend.model.response.ForFindCategory;

import com.example.cafebackend.model.response.ForFindProdcut.ForProductFormResponse;
import lombok.Data;

import java.util.List;

@Data
public class ForProdFormOfCategoryResponse {

    private String cateId;

    private String cateNameTh;

    private String cateNameEng;

    private Boolean isEnable;

    private Boolean isRecommend;

    private List<ForProductFormResponse> products;

}
