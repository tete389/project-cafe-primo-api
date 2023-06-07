package com.example.cafebackend.model.response.Employee;

import lombok.Data;

@Data
public class TypeEmpResponse {

    private String typeId;

    private String typeName;

    private Boolean typeSale;

    private String typeStatus;

    private Integer prodCount;

}
