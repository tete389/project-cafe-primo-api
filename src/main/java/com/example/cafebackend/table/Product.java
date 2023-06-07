package com.example.cafebackend.table;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "prod_id", length = 15, nullable = false, unique = true)
    private String prodId;

    @Column(name = "prod_form", length = 60, nullable = false)
    private String prodForm;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "image")
    private String image;

    @Column(name = "price", length = 12)
    private Double price;

    @Column(name = "is_for_sale")
    private Boolean isForSale;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "bonus_point", length = 12)
    private Double bonusPoint;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_prod_id")
    private BaseProduct baseProduct;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "prod_cate",
            joinColumns = @JoinColumn(name = "prod_id", referencedColumnName = "prod_id"),
            inverseJoinColumns = @JoinColumn(name = "cate_id", referencedColumnName = "cate_id"))
    private List<Category> category = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "prod_add",
            joinColumns = @JoinColumn(name = "prod_id", referencedColumnName = "prod_id"),
            inverseJoinColumns = @JoinColumn(name = "add_on_id", referencedColumnName = "add_on_id"))
    private List<AddOn> addOn = new ArrayList<>();



}
