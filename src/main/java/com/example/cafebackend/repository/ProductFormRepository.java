package com.example.cafebackend.repository;

import com.example.cafebackend.table.ProductForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductFormRepository extends JpaRepository<ProductForm, String> {

    @Query(value = "SELECT * FROM product_form p INNER JOIN base_product b ON p.base_prod_id=b.base_prod_id WHERE p.base_prod_id= :baseId AND p.is_enable='true' ORDER BY p.prod_form ASC", nativeQuery = true)
    List<ProductForm> findByProductBaseId(@Param("baseId") String cateId);

    @Query(value = "SELECT * FROM product_form p INNER JOIN prod_cate pc ON p.prod_form_id=pc.prod_id WHERE pc.cate_id= :cateId ORDER BY p.prod_form ASC", nativeQuery = true)
    List<ProductForm> findByCategoryCateId(@Param("cateId") String cateId);

    @Query(value = "SELECT * FROM product_form p INNER JOIN ingredient ing ON p.prod_form_id=ing.prod_id WHERE ing.mate_id= :mateId ORDER BY p.prod_form ASC", nativeQuery = true)
    List<ProductForm> findByIngredientsIdMateId(@Param("mateId") String mateId);

    @Query(value = "SELECT * FROM product_form p INNER JOIN add_on ad ON p.prod_form_id=ad.prod_id WHERE ad.add_on_id= :addId ORDER BY p.prod_form ASC", nativeQuery = true)
    List<ProductForm> findByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT * FROM product_form p WHERE p.prod_form_id= :formId AND p.is_enable='true'", nativeQuery = true)
    Optional<ProductForm> findByProductId(@Param("formId") String mateId);


    @Query(value = "SELECT * FROM product_form p WHERE p.prod_form= :prodForm ORDER BY p.prod_name ASC", nativeQuery = true)
    List<ProductForm> findByProductForm(@Param("prodForm") String mateId);

    @Query(value = "SELECT * FROM product p ORDER BY p.prod_name ASC", nativeQuery = true)
    List<ProductForm> findAllProduct();


    //boolean existsByProdName(String name);

    boolean existsByCategoryCateId(String cateId);

    boolean existsByAddOnAddOnId(String addId);

    boolean existsByProdForm(String form);

    boolean existsByMaterialUsedMaterialMateId(String mateId);


    @Query(value = "SELECT COUNT(p.prod_id) FROM product p INNER JOIN combine c ON p.prod_id=c.prod_id WHERE p.prod_status='enable' AND c.cate_id= :cateId", nativeQuery = true)
    Integer findCountProductOfCategory(@Param("cateId") String cate);



}
