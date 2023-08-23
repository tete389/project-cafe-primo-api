package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "emp_id", length = 36, nullable = false, updatable = false, unique = true)
    private String empId;

    @Column(name = "username", length = 36, nullable = false, updatable = false, unique = true )
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 36, nullable = false)
    private String password;

    @Column(name = "emp_name", length = 24, nullable = false)
    private String empName;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

}
