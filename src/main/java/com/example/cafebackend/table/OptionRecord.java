package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "option_record")
public class OptionRecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "option_rec_id", length = 36 ,nullable = false, updatable = false, unique = true)
    private String optionRecId;

    @Column(name = "option", length = 36)
    private String option;

    @Column(name = "option_price", length = 12)
    private Double optionPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_rec_id")
    private ProductRecord productRecord;

}

