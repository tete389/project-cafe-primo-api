package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "material_record")
public class MaterialRecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "add_on_record_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String addOnRecordId;

    @Column(name = "used_count", length = 36)
    private Double usedCount;

    @Column(name = "mate_name", length = 12)
    private String mateName;

    @Column(name = "mate_unit", length = 12)
    private String mateUnit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}

