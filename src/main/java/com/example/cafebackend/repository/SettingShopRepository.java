package com.example.cafebackend.repository;

import com.example.cafebackend.table.SettingShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingShopRepository extends JpaRepository<SettingShop, String> {

    //List<OrderDetailOption> findAllByOrderOrderId(String orderId);

//    @Query(value = "SELECT odo.* FROM order_detail_option odo inner join order_detail_product odp on odo.odt_prod_id = odp.odt_prod_id inner join \"order\" o on odp.order_id = o.order_id WHERE o.status = :success and cast(o.order_date as date) BETWEEN cast(:startDate as date) and cast(:endDate as date)", nativeQuery = true)
//    List<OrderDetailOption> findOrderDetailOptionBetweenDate(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String success);

}
