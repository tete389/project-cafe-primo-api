package com.example.cafebackend.repository;

import com.example.cafebackend.table.ProductForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductFormRepository extends JpaRepository<ProductForm, Long> {

    @Query(value = "SELECT * FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id= :baseId AND pf.is_delete = false ORDER BY pf.prod_form_id ASC", nativeQuery = true)
    List<ProductForm> findProductFormByBaseId(@Param("baseId") String baseId);

    @Query(value = "SELECT * FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id= :baseId AND pf.is_delete = false ORDER BY pf.prod_form_id ASC", nativeQuery = true)
    Page<ProductForm> findProductFormByBaseId(String baseId, Pageable pageable);

    @Query(value = "SELECT pf.prod_form_th FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id= :baseId AND pf.is_delete = false ORDER BY pf.prod_form_id ASC", nativeQuery = true)
    List<String> findFormThByBaseId(@Param("baseId") String baseId);

    @Query(value = "SELECT * FROM product_form pf INNER JOIN add_on ad ON pf.prod_form_id=ad.prod_form_id WHERE ad.add_on_id= :addId AND pf.is_delete = false ORDER BY p.price ASC , pf.prod_form_th ASC", nativeQuery = true)
    List<ProductForm> findByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT * FROM product_form pf WHERE pf.is_delete = false ORDER BY pf.prod_form_id ASC", nativeQuery = true)
    List<ProductForm> findProductFormAllASC();

    @Query(value = "SELECT * FROM product_form pf WHERE pf.is_delete = false ORDER BY pf.price ASC , pf.prod_form_th ASC", nativeQuery = true)
    Page<ProductForm> findProductFormAllASCPageable(Pageable pageable);

    @Query(value = "SELECT * FROM product_form pf WHERE pf.prod_form_id= :formId AND pf.is_delete = false", nativeQuery = true)
    Optional<ProductForm> findProductFormById(@Param("formId") Long formId);

    @Query(value = "SELECT min(pf.price) FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id =:baseId", nativeQuery = true)
    Double findMinPrice(@Param("baseId") String baseId);

    // boolean existsByProdName(String name);

    @Query(value = "SELECT * FROM product_form pf INNER JOIN material_used mu ON pf.prod_form_id = mu.prod_form_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.mate_id= :mateId AND pf.is_delete = false ORDER BY pf.price ASC , pf.prod_form_th ASC", nativeQuery = true)
    List<ProductForm> findProductFormByMateId(@Param("mateId") String mateId);

    boolean existsByProdFormTh(String form);

    boolean existsByProdFormEng(String form);

    boolean existsByMaterialUsedMaterialMateId(String mateId);

}
