package com.example.cafebackend.repository;

import com.example.cafebackend.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsByCateName(String name);

    Optional<Category> findByCateName(String name);

    @Query(value = "SELECT * FROM category c ORDER BY c.cate_name ASC", nativeQuery = true)
    List<Category> findListCategory();
}