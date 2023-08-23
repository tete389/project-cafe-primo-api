package com.example.cafebackend.model.response.ForOrder;

import com.example.cafebackend.table.OrderDetailMaterial;
import com.example.cafebackend.table.OrderDetailProduct;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderPd_Mt_Response {

    private String orderId;

    private String OrderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    private Double totalDetailPrice;

    private Double discount;

    private Double orderPrice;

    private String note;

    private List<OrderDetailProduct> orderDetailProducts = new ArrayList<>();

    private List<OrderDetailMaterial> orderDetailMaterials = new ArrayList<>();




}
