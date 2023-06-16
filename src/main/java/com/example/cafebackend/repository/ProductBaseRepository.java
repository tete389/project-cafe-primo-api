package com.example.cafebackend.repository;

import com.example.cafebackend.table.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductBaseRepository extends JpaRepository<ProductBase, String> {

//    @Query(value = "SELECT * FROM product p ORDER BY p.prod_title ASC", nativeQuery = true)
//    List<ProductBase> findAllBaseProduct();


//    @Query(value = "SELECT p FROM product_detail p WHERE p.prodName LIKE :name%")
//    List<BaseProduct> findByProdNameVerify(@Param("name") String name);


//    @Query(value = "SELECT p FROM product_detail p WHERE p.prodStatus='enable' AND p.type.typeId= :typeId")
//    List<ProductDetail> findByTypeTypeId(@Param("typeId") String typeId);

    boolean existsByProdTitle(String name);


//    @Query(value = "SELECT COUNT(p.prodId) FROM product_detail p WHERE p.prodStatus='enable' AND p.type.typeId= :typeId")
//    Integer findCountProdOfType(@Param("typeId") String type);


}
