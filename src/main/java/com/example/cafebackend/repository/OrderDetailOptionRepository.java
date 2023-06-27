package com.example.cafebackend.repository;

import com.example.cafebackend.table.OrderDetailOption;
import com.example.cafebackend.table.OrderDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailOptionRepository extends JpaRepository<OrderDetailOption, String> {

    //List<OrderDetailOption> findAllByOrderOrderId(String orderId);

    @Query(value = "SELECT odo.* FROM order_detail_option odo inner join order_detail_product odp on odo.odt_prod_id = odp.odt_prod_id inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :success and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailOption> findOrderDetailOptionBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("success") String success);
}
