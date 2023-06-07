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

//    @JsonIgnore
//    @OneToMany(mappedBy = "additional", cascade = CascadeType.ALL)
//    private List<AdditionalDetail> additionalDetails;

    @JsonIgnore
    @ManyToMany(mappedBy = "addOn", fetch = FetchType.LAZY)
    private List<Product> product = new ArrayList<>();

    @OneToMany(mappedBy = "addOn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();


}
