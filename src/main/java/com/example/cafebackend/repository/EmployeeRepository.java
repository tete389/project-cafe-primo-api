package com.example.cafebackend.repository;

import com.example.cafebackend.table.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    boolean existsByEmpLoginId(String loginId);

    boolean existsByEmpName(String name);

    Optional<Employee> findByEmpLoginId(String LoginId);
}
