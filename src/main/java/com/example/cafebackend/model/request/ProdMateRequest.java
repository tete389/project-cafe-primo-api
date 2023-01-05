package com.example.cafebackend.model.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProdMateRequest {

    private List<Integer> prodId = new ArrayList<>();

    private List<Integer> mateId = new ArrayList<>();

}
