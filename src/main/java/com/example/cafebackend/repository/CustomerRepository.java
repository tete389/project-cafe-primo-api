package com.example.cafebackend.repository;

import com.example.cafebackend.table.Customer;
import com.example.cafebackend.table.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByPhoneNumber(String PhoneNumber);

    //boolean existsByEmpName(String name);

    Optional<Customer> findByPhoneNumber(String phone);
}
