package com.example.cafebackend.repository;

import com.example.cafebackend.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
