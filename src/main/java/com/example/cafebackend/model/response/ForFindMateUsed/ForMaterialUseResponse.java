package com.example.cafebackend.model.response.ForFindMateUsed;

import com.example.cafebackend.table.Option;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import lombok.Data;

@Data
public class ForMaterialUseResponse {

    private Double amountUsed;

    private ProductBase productBase;

    private ProductForm productForm;

    private Option option;
}
