package com.example.cafebackend.repository;

import com.example.cafebackend.table.Ingredient;
import com.example.cafebackend.table.IngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, IngredientKey> {


}
