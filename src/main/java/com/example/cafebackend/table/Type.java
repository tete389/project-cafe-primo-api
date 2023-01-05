package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", nullable = false, unique = true)
    private Integer typeId;

    @Column(name = "type_name", length = 36, nullable = false)
    private String typeName;

    @Column(name = "type_status", nullable = false)
    private String typeStatus;

    @Column(name = "type_price", nullable = false)
    private Double typePrice;


//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "prod_type",
//            joinColumns = @JoinColumn(name = "type_id", referencedColumnName = "type_id"),
//            inverseJoinColumns = @JoinColumn(name = "prod_id", referencedColumnName = "prod_id"))
//    private List<Product> product = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    private Product product;

}
