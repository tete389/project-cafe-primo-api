package com.example.cafebackend.model.response;

import com.example.cafebackend.table.Option;
import lombok.Data;

import java.util.List;

@Data
public class ForAddOnResponse {

    private String AddOnId;

    private String AddOnTitle;

    private Boolean isManyOptions;

    private Boolean isEnable;

    private String description;

    private List<Option> options;

}
