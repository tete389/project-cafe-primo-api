package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false, unique = true)
    private Integer orderId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_number", length = 36, unique = true)
    private Integer orderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "order_create_date")
    private Date orderCreateDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_total_price", length = 12)
    private Double orderTotalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();


    public Order() {
        orderCreateDate = new Date();
    }
}
