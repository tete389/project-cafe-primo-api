package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;
import lombok.Data;

import java.util.List;

@Data
public class ForProductFormAddOnOptionResponse {

    private String prodFormId;

    private String prodForm;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private Double price;

    private String description;

    private List<ForAddOnResponse> addOnOption;

}
