package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "material_used")
public class MaterialUsed {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "mate_used_id", length = 36, nullable = false, updatable = false, unique = true)
    private String mateUsedId;

    @Column(name = "amount_used", length = 36)
    private Double amountUsed;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "prod_base_id")
    private ProductBase productBase;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_form_id")
    private ProductForm productForm;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    private Material material;

    @PreRemove
    private void removeProductFromMaterialUsed() {
        if (!Objects.isNull(productForm)) {
            ProductForm prodForm = productForm;
            prodForm.getMaterialUsed().remove(this);
        }

        if (!Objects.isNull(productBase)) {
            ProductBase prodBase = productBase;
            prodBase.getMaterialUsed().remove(this);
        }

        if (!Objects.isNull(option)) {
            Option op = option;
            op.getMaterialUsed().remove(this);
        }

        if (!Objects.isNull(material)) {
            Material mate = material;
            mate.getMaterialUsed().remove(this);
        }
    }

    public void deleteMaterialUsed() {
        if (!Objects.isNull(productForm)) {
            ProductForm prodForm = productForm;
            prodForm.getMaterialUsed().remove(this);
        }

        if (!Objects.isNull(productBase)) {
            ProductBase prodBase = productBase;
            prodBase.getMaterialUsed().remove(this);
        }

        if (!Objects.isNull(option)) {
            Option op = option;
            op.getMaterialUsed().remove(this);
        }

        if (!Objects.isNull(material)) {
            Material mate = material;
            mate.getMaterialUsed().remove(this);
        }
    }

    //
    // @PreRemove
    // private void removeMaterialFromMaterialUsed() {
    // Material mate = material;
    // mate.getMaterialUsed().remove(this);
    // }
}
