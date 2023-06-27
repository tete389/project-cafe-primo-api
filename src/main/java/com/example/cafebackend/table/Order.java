package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "order_number", length = 36)
    private String OrderNumber;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Column(name = "order_date")
//    private Date orderDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "order_date")
    private LocalDateTime orderDate;

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

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailProduct> orderDetailProducts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailMaterial> orderDetailMaterials = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PointDetail pointDetail;

    public Order() {
        //orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        orderDate = LocalDateTime.now();
    }

}
