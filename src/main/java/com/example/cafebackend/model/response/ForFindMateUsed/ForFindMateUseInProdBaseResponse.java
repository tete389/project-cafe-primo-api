package com.example.cafebackend.model.response.ForFindMateUsed;

import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import lombok.Data;

import java.util.List;

@Data
public class ForFindMateUseInProdBaseResponse {

    private String prodBaseId;

    private String prodTitleTh;

    private String prodTitleEng;

    private String image;

    private Boolean isEnable;

    private Boolean isMaterialEnable;

    private String description;

    private List<MaterialUsedNec> materialInfoUsed;

}
