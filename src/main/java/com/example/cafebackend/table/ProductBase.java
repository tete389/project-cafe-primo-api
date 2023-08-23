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

    @Column(name = "image")
    private String image;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "is_material_enable")
    private Boolean isMaterialEnable;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "productBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductForm> productForms = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "productBase", fetch = FetchType.LAZY)
    private List<Category> category = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productBase", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialUsed> materialUsed = new ArrayList<>();

    @PreRemove
    private void removeCategoryProduct() {
        if (!category.isEmpty()) {
            for (Category category1 : category) {
                category1.getProductBase().remove(this);
            }
        }


    }

}
