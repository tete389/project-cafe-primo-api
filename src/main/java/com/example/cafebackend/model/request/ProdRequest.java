package com.example.cafebackend.model.request;

import com.example.cafebackend.table.Option;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProdRequest {

    private String prodId;

    private Integer amount;

    private List<Option> options = new ArrayList<>();


}
