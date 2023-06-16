package com.example.cafebackend.model.response;

import com.example.cafebackend.model.response.ForListIdName.ProdNec;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForMaterialResponse {

    private String mateId;

    private String mateName;

    private Boolean isEnable;

    private Double stock;

    private List<ProdNec> prods = new ArrayList<>();

}
