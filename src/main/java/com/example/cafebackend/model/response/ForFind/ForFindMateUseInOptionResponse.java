package com.example.cafebackend.model.response.ForFind;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.MaterialUsed;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateUseInOptionResponse {

    private String optionId;

    private String optionName;

    private Double price;

    private Boolean isEnable;

    private AddOn addOn;

    private List<MaterialUsedNec>  materialInfoUsed;

}
