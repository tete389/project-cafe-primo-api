package com.example.cafebackend.repository;

import com.example.cafebackend.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT MAX(o.orderCreateDate) FROM Order o ", nativeQuery = true)
    String findByOrderCreateDateAsc();

    @Query(value = "SELECT COUNT(o.orderNumber) FROM Order o WHERE o.orderCreateDate LIKE :today%", nativeQuery = true)
    Integer findByOrderToDay(@Param("today") String toDay);

}
