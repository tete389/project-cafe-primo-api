package com.example.cafebackend.repository;

import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
