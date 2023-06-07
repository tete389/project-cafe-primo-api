package com.example.cafebackend.model.response;

import com.example.cafebackend.model.response.ForListIdName.ProdNec;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForCategoryResponse {

    private String cateId;

    private String cateName;

    private Boolean isEnable;

    private List<ProdNec> prods = new ArrayList<>();

}
