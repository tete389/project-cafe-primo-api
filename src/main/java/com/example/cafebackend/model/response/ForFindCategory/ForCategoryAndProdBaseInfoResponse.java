package com.example.cafebackend.model.response.ForFindCategory;

import com.example.cafebackend.model.response.ForFindProdcut.ForFindMateEnableInPBResponse;
import lombok.Data;

import java.util.List;

@Data
public class ForCategoryAndProdBaseInfoResponse {

    private String cateId;

    private String cateName;

    private Boolean isEnable;

    private Boolean isRecommend;

    private List<ForFindMateEnableInPBResponse> products;

}
