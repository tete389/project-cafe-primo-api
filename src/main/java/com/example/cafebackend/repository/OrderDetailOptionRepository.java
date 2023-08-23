package com.example.cafebackend.repository;

import com.example.cafebackend.table.OrderDetailOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderDetailOptionRepository extends JpaRepository<OrderDetailOption, String> {

    //List<OrderDetailOption> findAllByOrderOrderId(String orderId);

    @Query(value = "SELECT odo.* FROM order_detail_option odo inner join order_detail_product odp on odo.odt_prod_id = odp.odt_prod_id inner join \"order\" o on odp.order_id = o.order_id WHERE cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailOption> findOrderDetailOptionBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT odo.* FROM order_detail_option odo inner join order_detail_product odp on odo.odt_prod_id = odp.odt_prod_id inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    List<OrderDetailOption> findOrderDetailOptionBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String status);

    @Query(value = "SELECT odo.option_name FROM order_detail_option odo inner join order_detail_product odp on odo.odt_prod_id = odp.odt_prod_id inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :status and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Set<String> findOrderDetailOptionNameBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String status);

    @Query(value = "SELECT COUNT(odo.option_name) FROM order_detail_option odo inner join order_detail_product odp on odo.odt_prod_id = odp.odt_prod_id inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :status and odo.option_name = :optName and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
    Integer findOrderDetailOptionCountBetweenDateStatus(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String status, @Param("optName") String optName);
}
