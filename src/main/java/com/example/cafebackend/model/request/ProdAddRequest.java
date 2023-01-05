package com.example.cafebackend.model.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProdAddRequest {

    private List<Integer> prodId = new ArrayList<>();

    private List<Integer> addId = new ArrayList<>();

}
