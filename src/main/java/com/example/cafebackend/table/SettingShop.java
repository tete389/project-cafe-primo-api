package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;


@Data
@Entity
@Table(name = "setting_shop")
public class SettingShop {

    @Id
    @Column(name = "set_shop_id", length = 36, nullable = false, updatable = false, unique = true)
    private String setShopId;

    @Column(name = "point_spend_rate")
    private Double pointSpendRate;

    @Column(name = "point_collect_rate")
    private Double pointCollectRate;

    @Column(name = "vat_rate")
    private Double vatRate;

   @Column(name = "is_open_shop")
   private Boolean isOpenShop;

   @Column(name = "is_close_shop")
   private Boolean isCloseShop;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "open_date")
    private LocalTime openDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "closed_date")
    private LocalTime closedDate;



}
