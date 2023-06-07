package com.example.cafebackend.model.response;

import com.example.cafebackend.model.response.ForListIdName.ProdNec;
import com.example.cafebackend.table.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForMaterialResponse {

    private String mateId;

    private String mateName;

    private Boolean isEnable;

    private Double mateStock;

    private List<ProdNec> prods = new ArrayList<>();

}
