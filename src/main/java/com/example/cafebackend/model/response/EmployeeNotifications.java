package com.example.cafebackend.model.response;

import lombok.Data;

@Data
public class EmployeeNotifications {

    private String countOrderNotPayment;
    private String countOrderMaking;
    private String countOrderReceive;
    private String countOrderSuccess;
    private String countOrderKeep;
    private String countOrderCancel;
    private String countMaterialLowStock;

}
