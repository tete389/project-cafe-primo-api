package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @Column(name = "total_detail_price", length = 12)
    private Double totalDetailPrice;

    @Column(name = "discount", length = 12)
    private Double discount;

    @Column(name = "order_price", length = 12)
    private Double orderPrice;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetailProduct> orderDetailProducts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetailMaterial> orderDetailMaterials = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetailPoint> orderDetailPoint = new ArrayList<>();

    public Order() {
        //orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        orderDate = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
    }

}
