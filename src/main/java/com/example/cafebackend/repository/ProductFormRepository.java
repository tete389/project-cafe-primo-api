package com.example.cafebackend.repository;

import com.example.cafebackend.table.ProductForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductFormRepository extends JpaRepository<ProductForm, String> {

    @Query(value = "SELECT * FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id= :baseId ORDER BY pf.prod_form ASC", nativeQuery = true)
    List<ProductForm> findProductFormByBaseId(@Param("baseId") String baseId);

    @Query(value = "SELECT pf.prod_form FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id= :baseId ORDER BY pf.prod_form ASC", nativeQuery = true)
    List<String> findFormByBaseId(@Param("baseId") String baseId);

    @Query(value = "SELECT * FROM product_form p INNER JOIN add_on ad ON p.prod_form_id=ad.prod_id WHERE ad.add_on_id= :addId ORDER BY p.prod_form ASC", nativeQuery = true)
    List<ProductForm> findByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT * FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id = pb.prod_base_id ORDER BY pb.prod_title ASC", nativeQuery = true)
    List<ProductForm> findAllProduct();


    @Query(value = "SELECT min(pf.price) FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id=pb.prod_base_id WHERE pf.prod_base_id= :baseId", nativeQuery = true)
    Double findMinPrice(@Param("baseId") String cateId);

    //boolean existsByProdName(String name);

    @Query(value = "SELECT * FROM product_form pf INNER JOIN material_used mu ON pf.prod_form_id = mu.prod_form_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.mate_id= :mateId ", nativeQuery = true)
    List<ProductForm> findProdFormByMateId(String mateId);

    boolean existsByAddOnAddOnId(String addId);

    boolean existsByProdForm(String form);

    boolean existsByMaterialUsedMaterialMateId(String mateId);




}
