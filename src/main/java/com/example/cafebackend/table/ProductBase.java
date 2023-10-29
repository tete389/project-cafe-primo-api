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
    @Column(name = "prod_base_id", length = 36, nullable = false, unique = true)
    private String prodBaseId;

    @Column(name = "prod_title_th", length = 60, nullable = false)
    private String prodTitleTh;

    @Column(name = "prod_title_eng", length = 60, nullable = false)
    private String prodTitleEng;

    @Column(name = "image")
    private String image;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "is_material_enable")
    private Boolean isMaterialEnable;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "productBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductForm> productForms = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cate_prod", joinColumns = @JoinColumn(name = "prod_base_id", referencedColumnName = "prod_base_id"), inverseJoinColumns = @JoinColumn(name = "cate_id", referencedColumnName = "cate_id"))
    private List<Category> category = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productBase", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialUsed> materialUsed = new ArrayList<>();

    @PreRemove
    private void removeCategoryProduct() {
        if (!category.isEmpty()) {
            category.forEach(e -> e.getProductBase().remove(this));

        }

    }

}
