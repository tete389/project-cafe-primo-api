package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @JsonIgnore
    @EmbeddedId
    private IngredientKey id = new IngredientKey();

    @JsonIgnore
    @ManyToOne
    @MapsId("prodId")
    @JoinColumn(name = "prod_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @MapsId("mateId")
    @JoinColumn(name = "mate_id")
    private Material material;

    @Column(name = "mate_count", length = 36, nullable = false)
    private Double mateCount;

    @Column(name = "mate_unit", length = 36, nullable = false)
    private String mateUnit;

    @Column(name = "description")
    private String description;

    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;

//    public IngredientProduct(Product product, Material material, Integer useAmount) {
//        this.id = new IngredientProductKey(product.getProdId(), material.getMateId());
//        this.product = product;
//        this.material = material;
//        this.useAmount = useAmount;
//    }

}
