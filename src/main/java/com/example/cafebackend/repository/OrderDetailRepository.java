package com.example.cafebackend.repository;

import com.example.cafebackend.table.AdditionalDetail;
import com.example.cafebackend.table.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
