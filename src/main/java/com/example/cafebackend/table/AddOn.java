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
    @Column(name = "add_on_id", length = 36, nullable = false, unique = true)
    private String addOnId;

    @Column(name = "add_on_title_th", length = 36)
    private String addOnTitleTh;

    @Column(name = "add_on_title_eng", length = 36)
    private String addOnTitleEng;

    @Column(name = "is_many_options")
    private Boolean isManyOptions;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean isDelete;

    @JsonIgnore
    @ManyToMany(mappedBy = "addOn", fetch = FetchType.LAZY)
    private List<ProductForm> productForm = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "addOn", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    @PreRemove
    private void removeAddOn() {
        if (!productForm.isEmpty()) {
            productForm.forEach(e -> e.getAddOn().remove(this));

        }
    }

}
