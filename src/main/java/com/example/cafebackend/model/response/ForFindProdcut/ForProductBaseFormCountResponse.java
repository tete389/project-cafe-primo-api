package com.example.cafebackend.model.response.ForFindProdcut;

import lombok.Data;

public interface ForProductBaseFormCountResponse {

    String getProdBaseId();

    String getDescription();

    String getImage();

    // String getIsDelete();

    boolean getIsEnable();

    boolean getIsMaterialEnable();

    String getProdTitleEng();

    String getProdTitleTh();

    Number getFromCount();

}
