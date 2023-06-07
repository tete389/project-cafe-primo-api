package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order")
public class Order {


//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    @Column(name = "order_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String orderId;

    @Column(name = "number", length = 36)
    private String number;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private String createDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price", length = 12)
    private Double totalPrice;

    @Column(name = "total_bonus_point", length = 12)
    private Double totalBonusPoint;

    @Column(name = "note")
    private String note;

    @Column(name = "responsible")
    private String responsible;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductRecord> productRecords = new ArrayList<>();


    public Order() {
        createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
