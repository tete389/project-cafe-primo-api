package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "additionalDetail")
public class AdditionalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_detail_id", nullable = false, updatable = false, unique = true)
    private Integer addDetailId;

    @Column(name = "add_amount", length = 36)
    private Integer addAmount;

    @Column(name = "add_price_s", length = 12)
    private Double addPriceS;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "add_id")
    private Additional additional;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", referencedColumnName = "order_detail_id")
    private OrderDetail orderDetail;

}

