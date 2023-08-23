package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail_point")
public class OrderDetailPoint {

    @Id
    @Column(name = "odt_point_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String odtPointId;

    @Column(name = "action", length = 12)
    private String action;

    @Column(name = "action_point", length = 12)
    private Double actionPoint;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    @PreRemove
    private void removeOrderFromOrderDetailPoint() {
//        Order ord  =  order;
//        ord.getOrderDetailPoint().remove(this);
    }
}

