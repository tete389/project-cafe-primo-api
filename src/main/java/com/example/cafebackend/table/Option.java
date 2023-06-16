package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "option")
public class Option {

    @Id
    @Column(name = "option_id", length = 15, nullable = false, unique = true)
    private String optionId;

    @Column(name = "option_Name", length = 36, unique = true)
    private String optionName;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_for_sale")
    private Boolean isForSale;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "add_on_id")
    private AddOn addOn;

    @OneToMany(mappedBy = "option", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaterialUsed> materialUsed = new ArrayList<>();

}
