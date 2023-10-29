package com.example.cafebackend.model.response.ForOrder;

import com.example.cafebackend.table.OrderDetailMaterial;
import com.example.cafebackend.table.OrderDetailPoint;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {

    private String orderId;

    private String OrderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    private String status;

    private Double totalDetailPrice;

    private Double discount;

    private Double orderPrice;

    private String note;

    private String customerName;

    private List<ForOrderDetailProrductResponse> orderDetailProducts = new ArrayList<>();

    private List<OrderDetailMaterial> orderDetailMaterials = new ArrayList<>();

    private List<OrderDetailPoint> orderDetailPoint = new ArrayList<>();

}
