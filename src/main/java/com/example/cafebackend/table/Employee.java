package com.example.cafebackend.table;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "emp_id", length = 36, nullable = false, updatable = false, unique = true)
    private String empId;

    @Column(name = "emp_login_id", length = 36, nullable = false, updatable = false, unique = true )
    private String empLoginId;

    @Column(name = "emp_password", length = 36, nullable = false)
    private String empPassword;

    @Column(name = "emp_name", length = 24, nullable = false)
    private String empName;

    @Column(name = "emp_tel", length = 12)
    private String empTel;
}
