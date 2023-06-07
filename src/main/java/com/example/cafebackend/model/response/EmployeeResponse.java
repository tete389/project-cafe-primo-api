package com.example.cafebackend.model.response;

import com.example.cafebackend.table.Employee;
import lombok.Data;

@Data
public class EmployeeResponse {

    private String message;

    private Object employee;
}
