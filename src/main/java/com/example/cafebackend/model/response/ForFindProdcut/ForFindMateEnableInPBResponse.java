package com.example.cafebackend.model.response.ForFindProdcut;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialEnableNec;
import com.example.cafebackend.table.ProductBase;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateEnableInPBResponse {

    private String prodBaseId;

    private String prodTitle;

    private String image;

    private Boolean isEnable;

    private String description;

    private List<MaterialEnableNec> materialUsed;


}
