package com.example.cafebackend.repository;

import com.example.cafebackend.table.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, String> {

    boolean existsByOptionName(String optionName);

    Optional<Option> findByAddOnAddOnId(String option);
}
