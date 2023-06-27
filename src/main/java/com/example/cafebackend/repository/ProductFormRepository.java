package com.example.cafebackend.repository;

import com.example.cafebackend.table.ProductForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductFormRepository extends JpaRepository<ProductForm, String> {

    @Query(value = "SELECT * FROM product_form pf INNER JOIN base_product pb ON pf.base_prod_id=pb.base_prod_id WHERE pf.base_prod_id= :baseId ORDER BY pf.prod_form ASC", nativeQuery = true)
    List<ProductForm> findFormByBaseId(@Param("baseId") String cateId);;

    @Query(value = "SELECT * FROM product_form p INNER JOIN add_on ad ON p.prod_form_id=ad.prod_id WHERE ad.add_on_id= :addId ORDER BY p.prod_form ASC", nativeQuery = true)
    List<ProductForm> findByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT * FROM product_form pf INNER JOIN product_base pb ON pf.prod_base_id = pb.prod_base_id ORDER BY pb.prod_title ASC", nativeQuery = true)
    List<ProductForm> findAllProduct();


    //boolean existsByProdName(String name);

    boolean existsByCategoryCateId(String cateId);

    boolean existsByAddOnAddOnId(String addId);

    boolean existsByProdForm(String form);

    boolean existsByMaterialUsedMaterialMateId(String mateId);


    @Query(value = "SELECT COUNT(p.prod_id) FROM product p INNER JOIN combine c ON p.prod_id=c.prod_id WHERE p.prod_status='enable' AND c.cate_id= :cateId", nativeQuery = true)
    Integer findCountProductOfCategory(@Param("cateId") String cate);







}
