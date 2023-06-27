package com.example.cafebackend.repository;

import com.example.cafebackend.table.OrderDetailMaterial;
import com.example.cafebackend.table.OrderDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailMaterialRepository extends JpaRepository<OrderDetailMaterial, String> {

    List<OrderDetailMaterial> findAllByOrderOrderId(String orderId);

    @Query(value = "SELECT odm.* FROM order_detail_material odm inner join \"order\" o on odm.order_id = o.order_id WHERE o.status = :success and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailMaterial> findOrderDetailMaterialBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("success") String success);
}
