package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "setting_value")
public class SettingValue {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "sett_Id", length = 36, nullable = false, updatable = false, unique = true)
    private String settId;

    @Column(name = "point_rate")
    private Double pointRate;

    @JsonIgnore
    @Column(name = "vat_rate")
    private Double vatRate;

    @Column(name = "is_open_shop")
    private Boolean isOpenShop;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "open_date")
    private LocalDateTime openDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "closed_date")
    private LocalDateTime closedDate;



}
