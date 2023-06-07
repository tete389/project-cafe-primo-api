package com.example.cafebackend.model.response;

import com.example.cafebackend.model.response.ForListIdName.ProdNec;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class ForAddOnResponse {

    private String AddOnId;

    private String AddOnTitle;

    private Boolean isManyOptions;

    private Boolean isEnable;


    private String description;

    private List<ProdNec> prods = new ArrayList<>();

}
