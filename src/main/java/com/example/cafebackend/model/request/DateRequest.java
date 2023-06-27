package com.example.cafebackend.model.request;


import lombok.Data;

@Data
public class DateRequest {

    private String dateStart;

    private String dateEnd;

    private String statusOrder;

}
