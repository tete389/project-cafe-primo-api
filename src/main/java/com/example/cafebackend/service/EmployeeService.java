package com.example.cafebackend.service;

import com.example.cafebackend.repository.EmployeeRepository;
import com.example.cafebackend.table.Employee;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Optional<Employee> findById(String id){
        return employeeRepository.findById(id);
    }


}
