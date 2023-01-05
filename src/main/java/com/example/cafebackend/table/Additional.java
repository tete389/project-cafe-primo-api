package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "additional")
public class Additional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id", nullable = false, updatable = false, unique = true)
    private Integer addId;

    @Column(name = "add_name", length = 36, unique = true)
    private String addName;

    @Column(name = "add_status")
    private String addStatus;

    @Column(name = "add_price", length = 12)
    private Double addPrice;

//    @JsonIgnore
//    @OneToMany(mappedBy = "additional", cascade = CascadeType.ALL)
//    private List<AdditionalDetail> additionalDetails;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "prod_add",
            joinColumns = @JoinColumn(name = "add_id", referencedColumnName = "add_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id", referencedColumnName = "prod_id"))
    private List<Product> product = new ArrayList<>();

}
