package com.example.cafebackend.model.response;

import com.example.cafebackend.table.ProductForm;
import lombok.Data;

import java.util.List;

@Data
public class ForCategoryResponse {

    private String cateId;

    private String cateName;

    private Boolean isEnable;

    private Boolean isRecommend;

    private List<ProductForm> products;

}
