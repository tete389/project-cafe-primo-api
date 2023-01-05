package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mate_id", nullable = false, unique = true)
    private Integer mateId;

    @Column(name = "mate_name", length = 36, nullable = false, unique = true)
    private String mateName;

    @Column(name = "mate_status")
    private String mateStatus;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Product> products;



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "prod_mate",
            joinColumns = @JoinColumn(name = "mate_id", referencedColumnName = "mate_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id", referencedColumnName = "prod_id"))
    private List<Product> product = new ArrayList<>();

}
