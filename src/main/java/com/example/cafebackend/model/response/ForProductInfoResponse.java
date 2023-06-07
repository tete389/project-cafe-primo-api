package com.example.cafebackend.model.response;//package com.example.cafebackend.model.response;


import com.example.cafebackend.model.response.ForListIdName.CategoryNec;
import com.example.cafebackend.model.response.ForListIdName.MaterialNec;
import com.example.cafebackend.model.response.ForListIdName.AddOnNec;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForProductInfoResponse {

    private String prodId;

    private String prodForm;

    private String prodName;

    private String image;

    private Boolean isEnable;

    private Boolean isForSale;

    private Double price;

    private Double bonusPoint;

    private String description;

    private String baseProdId;

    private List<CategoryNec> categories = new ArrayList<>();

    private List<MaterialNec> materials = new ArrayList<>();

    private List<AddOnNec> addOn = new ArrayList<>();

}
