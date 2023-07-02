package com.example.cafebackend.model.response.ForFindCategory;

import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseResponse;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForProdBaseOfCategoryResponse {

    private String cateId;

    private String cateName;

    private Boolean isEnable;

    private Boolean isRecommend;

    private List<ProductBase> products;

}
