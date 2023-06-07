package com.example.cafebackend.repository;

import com.example.cafebackend.table.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddOnRepository extends JpaRepository<AddOn, String> {

    boolean existsByAddOnTitle(String title);

    Optional<AddOn> findByAddOnTitle(String name);
}
