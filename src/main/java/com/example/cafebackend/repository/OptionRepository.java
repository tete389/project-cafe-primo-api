package com.example.cafebackend.repository;

import com.example.cafebackend.table.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, String> {

    boolean existsByOptionName(String optionName);

    Optional<Option> findByAddOnAddOnId(String option);

    @Query(value = "SELECT op.option_Name FROM option op INNER JOIN add_on ad ON op.add_on_id=ad.add_on_id WHERE op.add_on_id= :addId ORDER BY op.option_Name ASC", nativeQuery = true)
    List<String> findOptionNameByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT * FROM option op INNER JOIN material_used mu ON op.option_id = mu.option_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.mate_id= :mateId ", nativeQuery = true)
    List<Option> findOptionByMateId(String mateId);
}
