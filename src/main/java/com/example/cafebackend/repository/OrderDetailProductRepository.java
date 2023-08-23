package com.example.cafebackend.repository;

import com.example.cafebackend.table.OrderDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderDetailProductRepository extends JpaRepository<OrderDetailProduct, String> {

    List<OrderDetailProduct> findAllByOrderOrderId(String orderId);

    @Query(value = "SELECT odp.* FROM order_detail_product odp inner join \"order\" o on odp.order_id = o.order_id WHERE cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailProduct> findOrderDetailProductBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT odp.* FROM order_detail_product odp inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailProduct> findOrderDetailProductBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String stutus);

    @Query(value = "SELECT odp.prod_name FROM order_detail_product odp inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Set<String> findOrderDetailProductFormIdBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String stutus);

    @Query(value = "SELECT SUM(odp.quantity) FROM order_detail_product odp inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :status and odp.prod_name = :prodName and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Integer findOrderDetailProductCountBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String stutus,  @Param("prodName") String formId);
}
