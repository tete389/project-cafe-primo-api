package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product_base")
public class ProductBase {

    @Id
    @Column(name = "prod_base_id", length = 15, nullable = false, unique = true)
    private String prodBaseId;

    @Column(name = "prod_title", length = 60, nullable = false)
    private String prodTitle;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "productBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductForm> productForms = new ArrayList<>();

    @OneToMany(mappedBy = "productBase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaterialUsed> materialUsed = new ArrayList<>();


}
