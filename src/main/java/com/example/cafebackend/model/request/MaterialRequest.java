package com.example.cafebackend.model.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MaterialRequest {

    private String prodId;

    private List<IngredientRequest> ingredients = new ArrayList<>();

}
