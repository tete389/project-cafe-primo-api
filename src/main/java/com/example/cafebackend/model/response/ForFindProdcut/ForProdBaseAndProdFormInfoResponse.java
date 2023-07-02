package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.table.ProductForm;
import lombok.Data;

import java.util.List;

@Data
public class ForProdBaseAndProdFormInfoResponse {

    private String prodBaseId;

    private String prodTitle;

    private Boolean isEnable;

    private String description;

    private List<ForFindMateEnableAndAddOnInPFResponse> productFormList;


}
