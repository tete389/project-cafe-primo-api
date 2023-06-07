package com.example.cafebackend.repository;

import com.example.cafebackend.table.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, String> {

    boolean existsByMateName(String name);
}
