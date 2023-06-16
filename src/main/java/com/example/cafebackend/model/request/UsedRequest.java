package com.example.cafebackend.model.request;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsedRequest {

    private List<MateUsedRequest> mateUsed = new ArrayList<>();

    private String prodBaseId;

    private String prodFormId;

    private String optionId;

}
