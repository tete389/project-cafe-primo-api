package com.example.cafebackend.model.response.ForOrder;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRecentResponse {

    private String orderId;

    private String OrderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    private Double totalDetailPrice;

    private Double discount;

    private Double orderPrice;

    private String note;

    private Integer detailProductsCount;



}
