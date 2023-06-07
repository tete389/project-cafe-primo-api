package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "add_on_record")
public class AddOnRecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "add_on_record_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String addOnRecordId;

    @Column(name = "add_on_option", length = 36)
    private String addOnOption;

    @Column(name = "add_on_price", length = 12)
    private Double addOnPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_detail_id")
    private ProductRecord productRecord;

}

