package com.example.cafebackend.repository;

import com.example.cafebackend.table.Material;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterialRepository extends JpaRepository<Material, String> {

    @Query(value = "SELECT * FROM material m  WHERE m.is_delete = false ORDER BY m.mate_name ASC", nativeQuery = true)
    List<Material> findMaterialALL();

    @Query(value = "SELECT * FROM material m WHERE m.mate_id= :mateId AND m.is_delete = false", nativeQuery = true)
    Optional<Material> findMaterialById(String mateId);
    
    boolean existsByMateName(String name);

    @Query(value = "SELECT COUNT(m.stock) FROM material m  WHERE m.stock < 100 AND m.is_enable = true", nativeQuery = true)
    Integer findMaterialLowStock();
}
