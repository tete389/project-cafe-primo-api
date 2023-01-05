package com.example.cafebackend.model.request;

import com.example.cafebackend.table.Type;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProdTypeRequest {

    private List<Integer> prodId = new ArrayList<>();

    private List<Integer> typeId = new ArrayList<>();


}
