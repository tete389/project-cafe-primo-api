package com.example.cafebackend.repository;

import com.example.cafebackend.table.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductBaseRepository extends JpaRepository<ProductBase, String> {



    @Query(value = "SELECT * FROM product_base pb INNER JOIN material_used mu ON pb.prod_base_id = mu.prod_base_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.mate_id= :mateId ", nativeQuery = true)
    List<ProductBase> findProdBaseByMateId(String mateId);

    boolean existsByProdTitle(String name);




}
