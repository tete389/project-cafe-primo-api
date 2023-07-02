package com.example.cafebackend.model.response.ForFindMateUsed;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.AddOn;
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
