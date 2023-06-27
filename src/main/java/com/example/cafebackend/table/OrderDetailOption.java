package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail_option")
public class OrderDetailOption {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "odt_option_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String odtOptionId;

    @Column(name = "option_name", length = 36)
    private String optionName;

    @Column(name = "option_price", length = 12)
    private Double optionPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odt_prod_id")
    private OrderDetailProduct orderDetailProduct;

    @PreRemove
    private void removeDetailProductFromOrderDetailOption() {
        OrderDetailProduct ord  =  orderDetailProduct;
        ord.getOrderDetailOptions().remove(this);
    }

}

