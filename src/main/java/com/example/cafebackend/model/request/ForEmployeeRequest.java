package com.example.cafebackend.model.request;


import lombok.Data;

@Data
public class ForEmployeeRequest {

    private String username;

    private String password;

    private String empName;

    private String phoneNumber;

}
