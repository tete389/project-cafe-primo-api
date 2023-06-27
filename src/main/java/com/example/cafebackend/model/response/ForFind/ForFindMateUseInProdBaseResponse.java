package com.example.cafebackend.model.response.ForFind;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.MaterialUsed;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateUseInProdBaseResponse {

    private String prodBaseId;

    private String prodTitle;

    private Boolean isEnable;

    private String description;

    private List<MaterialUsedNec>  materialInfoUsed;

}
