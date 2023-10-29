package com.example.cafebackend.model.response.ForFindCategory;

import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ForCategoryProductBaseMinPriceResponse {

    private String cateId;

    private String cateNameTh;

    private String cateNameEng;

    private Boolean isEnable;

    private Boolean isRecommend;

    private List<ForProductBaseMinPriceResponse> productBasePrice;

}
