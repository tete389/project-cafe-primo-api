package com.example.cafebackend.model.response.ForFindMateUsed;

import lombok.Data;

import java.util.List;

@Data
public class ForMaterialResponse {

    private String mateId;

    private String mateName;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double stock;

    private List<ForMaterialUseResponse> materialUses;

}
