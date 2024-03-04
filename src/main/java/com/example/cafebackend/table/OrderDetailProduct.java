package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_detail_product")
public class OrderDetailProduct {

    @Id
    @Column(name = "odt_prod_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String odtProdId;

    @Column(name = "prod_name_th", length = 36)
    private String prodNameTh;

    @Column(name = "prod_name_eng", length = 36)
    private String prodNameEng;

    @Column(name = "prod_price", length = 12)
    private Double prodPrice;

    @Column(name = "option_price", length = 12)
    private Double optionPrice;

    @Column(name = "quantity", length = 12)
    private Double quantity;

    @Column(name = "detail_price", length = 12)
    private Double detailPrice;

    @Column(name = "prod_form_id", length = 36)
    private Long prodFormId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnore
    @OneToMany(mappedBy = "orderDetailProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetailOption> orderDetailOptions = new ArrayList<>();

    @PreRemove
    private void removeOrderFromOrderDetailProduct() {

    }

}

