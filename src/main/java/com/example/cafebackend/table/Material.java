package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "material")
public class Material {

    @Id
    @Column(name = "mate_id", length = 15, nullable = false, unique = true)
    private String mateId;

    @Column(name = "mate_name", length = 36, nullable = false)
    private String mateName;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "mate_stock", length = 36, nullable = false)
    private Double mateStock;

    @JsonIgnore
    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();


}