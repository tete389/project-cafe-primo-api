package com.example.cafebackend.model.request;

import com.example.cafebackend.table.AdditionalDetail;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailRequest {

    private Integer prodAmount;

    private Integer prodId;

    private Integer sweetId;

    private Integer typeId;

    private List<AddDetailRequest> addDetailRequestList;


}
