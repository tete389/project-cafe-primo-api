package com.example.cafebackend.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "cate_id", length = 36, nullable = false, unique = true)
    private String cateId;

    @Column(name = "cate_name_th", length = 60)
    private String cateNameTh;

    @Column(name = "cate_name_eng", length = 60)
    private String cateNameEng;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "is_recommend")
    private Boolean isRecommend;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean isDelete;

    @JsonIgnore
    @ManyToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductBase> productBase = new ArrayList<>();

    @PreRemove
    private void removeProductCategory() {
        if (!productBase.isEmpty()) {
            productBase.forEach(e -> e.getCategory().remove(this));

        }

    }

}
