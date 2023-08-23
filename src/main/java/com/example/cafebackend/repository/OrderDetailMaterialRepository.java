package com.example.cafebackend.repository;

import com.example.cafebackend.table.OrderDetailMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderDetailMaterialRepository extends JpaRepository<OrderDetailMaterial, String> {

    List<OrderDetailMaterial> findAllByOrderOrderId(String orderId);

    @Query(value = "SELECT odm.* FROM order_detail_material odm inner join \"order\" o on odm.order_id = o.order_id WHERE cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailMaterial> findOrderDetailMaterialBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT odm.* FROM order_detail_material odm inner join \"order\" o on odm.order_id = o.order_id WHERE o.status = :stats and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailMaterial> findOrderDetailMaterialBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("stats") String stats);

    @Query(value = "SELECT odm.mate_name FROM order_detail_material odm inner join \"order\" o on odm.order_id = o.order_id WHERE o.status = :stats and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Set<String> findOrderDetailMaterialNameBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("stats") String stats);

    @Query(value = "SELECT COUNT(odm.mate_name) FROM order_detail_material odm inner join \"order\" o on odm.order_id = o.order_id WHERE o.status = :stats and odm.mate_name = :mateName and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Integer findOrderDetailMaterialCountBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("stats") String stats, @Param("mateName") String mateName);
}
