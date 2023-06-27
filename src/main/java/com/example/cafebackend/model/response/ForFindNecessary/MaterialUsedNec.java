package com.example.cafebackend.model.response.ForFindNecessary;

import lombok.Data;

@Data
public class MaterialUsedNec {

    private String mateId;

    private String mateName;

    private Double amountUsed;

    private String mateUnit;
}
