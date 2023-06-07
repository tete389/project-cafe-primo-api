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

    @Column(name = "isEnable")
    private Boolean isEnable;

    @JsonIgnore
    @ManyToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> product = new ArrayList<>();


    @PreRemove
    private void removeProductFromCategory() {
        for (Product prod : product) {
            prod.getCategory().remove(this);
        }
    }

}
