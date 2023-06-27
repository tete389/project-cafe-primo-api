package com.example.cafebackend.model.response;

import com.example.cafebackend.table.OrderDetailMaterial;
import com.example.cafebackend.table.PointDetail;
import com.example.cafebackend.table.OrderDetailProduct;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {

    private String orderId;

    private String OrderNumber;

    private LocalDateTime orderDate;

    private Double totalPrice;

    private Double totalBonusPoint;

    private String note;

    private String responsible;

    private List<OrderDetailProduct> orderDetailProducts = new ArrayList<>();

    private List<OrderDetailMaterial> orderDetailMaterials = new ArrayList<>();

    private PointDetail pointDetail;



}
