package com.example.cafebackend.model.response.ForFindProdcut;


import java.util.List;

import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;


@Data
public class ForProductFormDetailResponse {

    private Long prodFormId;

    private String prodFormTh;

     private String prodFormEng;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double price;

    private String description;

    private ProductBase productBase;

    private String productBaseId;

    private List<MaterialUsedNec> materialUsed;

    private List<AddOn> addOn;

}
