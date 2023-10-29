package com.example.cafebackend.repository;

import com.example.cafebackend.table.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT MAX(o.order_date) FROM order o", nativeQuery = true)
    String findByOrderCreateDateAsc();

    @Query(value = "SELECT COUNT(o.order_number) FROM \"order\" o WHERE cast(o.order_date as date) =:DateNow", nativeQuery = true)
    Integer findCountByOrderToDay(LocalDate DateNow);

    @Query(value = "SELECT COUNT(o.order_number) FROM \"order\"  o WHERE cast(o.order_date as date) =:DateNow AND o.status =:status", nativeQuery = true)
    Integer findCountByOrderToDayStatus(String status, LocalDate DateNow);

    // @Query(value = "SELECT * FROM \"order\" o WHERE cast(o.order_date as date) =
    // current_date ORDER BY o.order_date ASC", nativeQuery = true)
    @Query(value = "SELECT * FROM \"order\"  o WHERE cast(o.order_date as date) =:DateNow ORDER BY o.order_date ASC", nativeQuery = true)
    List<Order> findByOrderToDay(LocalDate DateNow);

    @Query(value = "SELECT * FROM \"order\" o WHERE  cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date) ORDER BY o.order_date ASC", nativeQuery = true)
    Page<Order> findOrderBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate,
            Pageable pageable);

    @Query(value = "SELECT * FROM \"order\" o WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date) ORDER BY o.order_date ASC", nativeQuery = true)
    Page<Order> findOrderBetweenDateByStats(String startDate, String endDate, String status, Pageable pageable);

    @Query(value = "SELECT SUM(o.order_price) FROM \"order\" o WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Integer findIncomeOfMonth(String startDate, String endDate, String status);

    @Query(value = "SELECT SUM(o.order_price) FROM \"order\" o WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Integer findIncomeOfWeek(String startDate, String endDate, String status);
}
