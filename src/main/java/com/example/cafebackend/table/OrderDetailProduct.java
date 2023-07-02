package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_detail_product")
public class OrderDetailProduct {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "odt_prod_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String odtProdId;

    @Column(name = "prod_name", length = 36)
    private String prodName;

    @Column(name = "prod_price", length = 12)
    private Double prodPrice;

    @Column(name = "prod_option_price", length = 12)
    private Double prodOptionPrice;

    @Column(name = "quantity", length = 12)
    private Double quantity;

    @Column(name = "detail_price", length = 12)
    private Double detailPrice;

//    @Column(name = "bonus_point", length = 12)
//    private Double bonusPoint;

    @Column(name = "prod_form_id", length = 36)
    private String prodFormId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "orderDetailProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetailOption> orderDetailOptions = new ArrayList<>();

    @PreRemove
    private void removeOrderFromOrderDetailProduct() {
        Order ord  =  order;
        ord.getOrderDetailProducts().remove(this);
    }

}

