package com.example.cafebackend.model.request;

import com.example.cafebackend.table.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductBaseInsertToCategoryRequest {

    private String prodBaseId;

    private List<Category> listCategory = new ArrayList<>();


}
