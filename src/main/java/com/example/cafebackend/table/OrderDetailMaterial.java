package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail_material")
public class OrderDetailMaterial {

    @Id
    @Column(name = "odt_mate_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String odtMateId;

    @Column(name = "amount_used", length = 36)
    private Double amountUsed;

    @Column(name = "mate_name", length = 12)
    private String mateName;

    @Column(name = "mate_unit", length = 12)
    private String mateUnit;

    @Column(name = "mate_id", length = 15)
    private String mateId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @PreRemove
    private void removeOrderFromOrderDetailMaterial() {
//        Order ord  =  order;
//        ord.getOrderDetailMaterials().remove(this);
    }

}

