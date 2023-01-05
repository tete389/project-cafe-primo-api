package com.example.cafebackend.repository;

import com.example.cafebackend.table.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
