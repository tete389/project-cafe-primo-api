package com.example.cafebackend.model.response.ForFindAddOnOpion;

import com.example.cafebackend.table.Option;
import lombok.Data;

import java.util.List;

@Data
public class ForAddOnResponse {

    private String addOnId;

    private String addOnTitleTh;

    private String addOnTitleEng;

    private Boolean isManyOptions;

    private Boolean isEnable;

    private String description;

    private List<Option> options;

}
