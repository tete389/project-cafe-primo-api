package com.example.cafebackend.model.response.ForOrder;

import java.util.ArrayList;
import java.util.List;

import com.example.cafebackend.table.OrderDetailOption;

import lombok.Data;

@Data
public class ForOrderDetailProrductResponse {
    private String odtProdId;

    private String prodNameTh;

    private String prodNameEng;

    private Double prodPrice;

    private Double optionPrice;

    private Double quantity;

    private Double detailPrice;

    private String prodFormId;

    private List<OrderDetailOption> orderDetailOptions = new ArrayList<>();
}
