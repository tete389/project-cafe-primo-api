package com.example.cafebackend.repository;

import com.example.cafebackend.table.MaterialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRecordRepository extends JpaRepository<MaterialRecord, String> {
}
