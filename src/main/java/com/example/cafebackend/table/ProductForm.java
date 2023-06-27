package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product_form")
public class ProductForm {

    @Id
    @Column(name = "prod_form_id", length = 15, nullable = false, unique = true)
    private String prodFormId;

    @Column(name = "prod_form", length = 60, nullable = false)
    private String prodForm;

    @Column(name = "image")
    private String image;

    @Column(name = "price", length = 12)
    private Double price;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "bonus_point", length = 12)
    private Double bonusPoint;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prod_base_id")
    private ProductBase productBase;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Ingredient> ingredients = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaterialUsed> materialUsed = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "productForm", fetch = FetchType.LAZY)
    private List<Category> category = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "prod_add",
            joinColumns = @JoinColumn(name = "prod_form_id", referencedColumnName = "prod_form_id"),
            inverseJoinColumns = @JoinColumn(name = "add_on_id", referencedColumnName = "add_on_id"))
    private List<AddOn> addOn = new ArrayList<>();


    @PreRemove
    private void removeAddOnProductFrom() {
        for (AddOn add : addOn) {
            add.getProductForm().remove(this);
        }

    }


}
