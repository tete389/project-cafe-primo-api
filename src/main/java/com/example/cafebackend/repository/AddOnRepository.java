package com.example.cafebackend.repository;

import com.example.cafebackend.table.AddOn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddOnRepository extends JpaRepository<AddOn, String> {

    boolean existsByAddOnTitleTh(String titleTh);

    boolean existsByAddOnTitleEng(String titleEng);

    @Query(value = "SELECT * FROM add_on add WHERE add.add_on_id= :addId AND add.is_delete = false", nativeQuery = true)
    Optional<AddOn> findAddOnById(String addId);

    @Query(value = "SELECT * FROM add_on add INNER JOIN prod_add pd  ON pd.add_on_id=add.add_on_id INNER JOIN product_form pf ON pf.prod_form_id=pd.prod_form_id WHERE pd.prod_form_id= :formId AND add.is_delete = false ORDER BY add.add_on_title_th ASC", nativeQuery = true)
    List<AddOn> findAddOnByProductFormId(Long formId);

    @Query(value = "SELECT * FROM add_on add WHERE add.is_delete = false ORDER BY add.add_on_title_th ASC", nativeQuery = true)
    List<AddOn> findAddOnAll();

    @Query(value = "SELECT * FROM add_on add WHERE add.add_on_title_th= :name AND add.is_delete = false", nativeQuery = true)
    Optional<AddOn> findByAddOnTitleTh(String name);

    @Query(value = "SELECT * FROM add_on add WHERE add.add_on_title_eng= :name AND add.is_delete = false", nativeQuery = true)
    Optional<AddOn> findByAddOnTitleEng(String name);
}
