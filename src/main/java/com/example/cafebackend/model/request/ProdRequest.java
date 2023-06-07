package com.example.cafebackend.model.request;

import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Ingredient;
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
