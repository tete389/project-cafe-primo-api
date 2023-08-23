package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "add_on")
public class AddOn {

    @Id
    @Column(name = "add_on_id", length = 15, nullable = false, unique = true)
    private String addOnId;

    @Column(name = "add_on_title", length = 36, unique = true)
    private String addOnTitle;

    @Column(name = "is_many_options")
    private Boolean isManyOptions;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "addOn", fetch = FetchType.LAZY)
    private List<ProductForm> productForm = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "addOn", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    @PreRemove
    private void removeAddOnProductFrom() {
        if (!productForm.isEmpty()){
            for (ProductForm form : productForm) {
                form.getAddOn().remove(this);
            }
        }

    }
}
