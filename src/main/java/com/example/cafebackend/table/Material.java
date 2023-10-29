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
    @Column(name = "mate_id", length = 36, nullable = false, updatable = false, unique = true)
    private String mateId;

    @Column(name = "mate_name", length = 36, nullable = false)
    private String mateName;

    @Column(name = "mate_Unit", length = 36, nullable = false)
    private String mateUnit;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "stock", length = 36, nullable = false)
    private Double stock;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialUsed> materialUsed = new ArrayList<>();

    /////////

}
