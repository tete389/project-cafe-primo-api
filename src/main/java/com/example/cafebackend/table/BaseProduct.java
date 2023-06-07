package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "base_product")
public class BaseProduct {

    @Id
    @Column(name = "base_prod_id", length = 15, nullable = false, unique = true)
    private String baseProdId;

    @Column(name = "prod_title", length = 60, nullable = false)
    private String prodTitle;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "description")
    private String description;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "type_id")
//    private Type type;

    @JsonIgnore
    @OneToMany(mappedBy = "baseProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();


}
