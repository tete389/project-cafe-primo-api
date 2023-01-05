package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id", nullable = false, updatable = false, unique = true)
    private Integer prodId;

    @Column(name = "prod_name", length = 36, nullable = false, unique = true)
    private String prodName;

    @Column(name = "prod_img")
    private String prodImg;

    @Column(name = "prod_status")
    private String prodStatus;

    @Column(name = "prod_price", length = 12)
    private Double prodPrice;

    @Column(name = "prod_time_process", length = 12)
    private Double prodTimeProcess;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "cate_id")
//    private Category categories;



    @ManyToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Category> category = new ArrayList<>();


    @ManyToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Material> material = new ArrayList<>();


//    @ManyToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<Type> type = new ArrayList<>();


    @OneToMany(targetEntity=Type.class ,mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Type> type = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Additional> additional = new ArrayList<>();


}
