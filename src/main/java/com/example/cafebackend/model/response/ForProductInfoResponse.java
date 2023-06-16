package com.example.cafebackend.model.response;//package com.example.cafebackend.model.response;


import com.example.cafebackend.model.response.ForListIdName.CategoryNec;
import com.example.cafebackend.model.response.ForListIdName.MaterialNec;
import com.example.cafebackend.model.response.ForListIdName.AddOnNec;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForProductInfoResponse {

    private String prodFormId;

    private String prodForm;

    private String image;

    private Boolean isEnable;

    private Double price;

    private Double bonusPoint;

    private String description;

    private ProductBase productBase;

    private List<CategoryNec> categories = new ArrayList<>();

    private List<MaterialNec> materials = new ArrayList<>();

    private List<AddOnNec> addOn = new ArrayList<>();

}
