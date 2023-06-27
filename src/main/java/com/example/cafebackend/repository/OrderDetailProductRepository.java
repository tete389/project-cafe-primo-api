package com.example.cafebackend.repository;

import com.example.cafebackend.table.Order;
import com.example.cafebackend.table.OrderDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailProductRepository extends JpaRepository<OrderDetailProduct, String> {

    List<OrderDetailProduct> findAllByOrderOrderId(String orderId);

    @Query(value = "SELECT odp.* FROM order_detail_product odp inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :success and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailProduct> findOrderDetailProductBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("success") String success);

}
