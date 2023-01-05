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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id", nullable = false, unique = true)
    private Integer cateId;

    @Column(name = "cate_name", length = 36, unique = true)
    private String cateName;

    @Column(name = "cate_status")
    private String cateStatus;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Product> products;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "prod_cate",
            joinColumns = @JoinColumn(name = "cate_id", referencedColumnName = "cate_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id", referencedColumnName = "prod_id"))
    private List<Product> product = new ArrayList<>();

}
