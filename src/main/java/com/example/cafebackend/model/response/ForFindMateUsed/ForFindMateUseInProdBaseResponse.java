package com.example.cafebackend.model.response.ForFindMateUsed;


import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
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
