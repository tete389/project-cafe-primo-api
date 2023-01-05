package com.example.cafebackend.model.request;

import com.example.cafebackend.table.Type;
import lombok.Data;

import java.util.List;

@Data
public class ProdRequest {

    private String prodName;

    private String prodImg;

    private String prodStatus;

    private Double prodPrice;

    private Double prodTimeProcess;

    private List<Type> prodType;

}
