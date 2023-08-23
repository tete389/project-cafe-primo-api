package com.example.cafebackend.model.request;

import com.example.cafebackend.table.Option;
import lombok.Data;

import java.util.List;

@Data
public class ProdRequest {

    private String prodFormId;

    private String quantity;

    private List<Option> options;


}
