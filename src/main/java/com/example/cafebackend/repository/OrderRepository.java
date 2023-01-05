package com.example.cafebackend.repository;

import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
