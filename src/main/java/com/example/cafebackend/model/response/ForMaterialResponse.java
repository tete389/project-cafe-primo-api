package com.example.cafebackend.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ForMaterialResponse {

    private String mateId;

    private String mateName;

    private Boolean isEnable;

    private Double stock;

    private List<ForMaterialUseResponse> materialUses;

}
