package com.example.cafebackend.repository;

import com.example.cafebackend.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT MAX(o.order_date) FROM order o", nativeQuery = true)
    String findByOrderCreateDateAsc();

    @Query(value = "SELECT COUNT(o.order_number) FROM \"order\"  o WHERE cast(o.order_date as date) = current_date AND o.status =:status", nativeQuery = true)
    Integer findCountByOrderToDay(@Param("status") String status);

    @Query(value = "SELECT * FROM \"order\"  o WHERE cast(o.order_date as date) = current_date ORDER BY o.order_date ASC", nativeQuery = true)
    List<Order> findByOrderToDay();

    @Query(value = "SELECT * FROM \"order\" o WHERE  cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date) ORDER BY o.order_date ASC", nativeQuery = true)
    List<Order> findOrderBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM \"order\" o WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date) ORDER BY o.order_date ASC", nativeQuery = true)
    List<Order> findOrderBetweenDateByStats(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String status);


}
