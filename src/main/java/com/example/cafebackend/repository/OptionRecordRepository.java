package com.example.cafebackend.repository;

import com.example.cafebackend.table.OptionRecord;
import com.example.cafebackend.table.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRecordRepository extends JpaRepository<OptionRecord, String> {

    //List<ProductRecord> findAllByOrderOrderId(String orderId);
}
