package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "cate_id", length = 15, nullable = false, unique = true)
    private String cateId;

    @Column(name = "cate_name", length = 60, unique = true)
    private String cateName;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "is_recommend")
    private Boolean isRecommend;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "cate_prod",
//            joinColumns = @JoinColumn(name = "cate_id", referencedColumnName = "cate_id"),
//            inverseJoinColumns = @JoinColumn(name = "prod_form_id", referencedColumnName = "prod_form_id"))
//    private List<ProductForm> productForm = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cate_prod_B",
            joinColumns = @JoinColumn(name = "cate_id", referencedColumnName = "cate_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_base_id", referencedColumnName = "prod_base_id"))
    private List<ProductBase> productBase = new ArrayList<>();


    @PreRemove
    private void removeProductCategory() {
//        for (ProductForm prod : productForm) {
//            prod.getCategory().remove(this);
//        }

        for (ProductBase prod : productBase) {
            prod.getCategory().remove(this);
        }
    }


}
