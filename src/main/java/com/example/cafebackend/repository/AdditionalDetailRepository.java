package com.example.cafebackend.repository;

import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.AdditionalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalDetailRepository extends JpaRepository<AdditionalDetail, Integer> {
}
