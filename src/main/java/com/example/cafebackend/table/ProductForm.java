package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "product_form")
public class ProductForm {

    @Id
    @Column(name = "prod_form_id", length = 15, nullable = false, unique = true)
    private String prodFormId;

    @Column(name = "prod_form_th", length = 60, nullable = false)
    private String prodFormTh;

    @Column(name = "prod_form_eng", length = 60, nullable = false)
    private String prodFormEng;

    @Column(name = "price", length = 12)
    private Double price;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "is_material_enable")
    private Boolean isMaterialEnable;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prod_base_id")
    private ProductBase productBase;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "productForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialUsed> materialUsed = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prod_add", joinColumns = @JoinColumn(name = "prod_form_id", referencedColumnName = "prod_form_id"), inverseJoinColumns = @JoinColumn(name = "add_on_id", referencedColumnName = "add_on_id"))
    private List<AddOn> addOn = new ArrayList<>();

    @PreRemove
    private void removeAddOnProductFrom() {
        if (!addOn.isEmpty()) {
            addOn.forEach(e -> e.getProductForm().remove(this));
        }
        if (!Objects.isNull(productBase)) {
            productBase.getProductForms().remove(this);
        }

        // if (!Objects.isNull(materialUsed) && !materialUsed.isEmpty()) {
        //     materialUsed.forEach(e -> e.);
        // }

    }

}
