package com.example.cafebackend.repository;

import com.example.cafebackend.table.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, String> {

    boolean existsByOptionNameTh(String optionName);

    boolean existsByOptionNameEng(String optionName);

    Optional<Option> findByAddOnAddOnId(String option);

    @Query(value = "SELECT * FROM option opt WHERE opt.option_id= :optionId AND opt.is_delete = false", nativeQuery = true)
    Optional<Option> findOptionById(String optionId);

    @Query(value = "SELECT * FROM option opt WHERE opt.is_delete = false ORDER BY opt.option_name_th ASC", nativeQuery = true)
    List<Option> findOptionAll();

    @Query(value = "SELECT * FROM option opt INNER JOIN add_on add ON opt.add_on_id=add.add_on_id WHERE  opt.add_on_id= :addOnId AND opt.is_delete = false ORDER BY opt.option_name_th ASC", nativeQuery = true)
    List<Option> findOptionByAddOnId(String addOnId);

    @Query(value = "SELECT op.option_name_th FROM option op INNER JOIN add_on ad ON op.add_on_id=ad.add_on_id WHERE op.add_on_id= :addId ORDER BY op.option_name_th ASC", nativeQuery = true)
    List<String> findOptionNameThByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT op.option_name_eng FROM option op INNER JOIN add_on ad ON op.add_on_id=ad.add_on_id WHERE op.add_on_id= :addId ORDER BY op.option_name_th ASC", nativeQuery = true)
    List<String> findOptionNameEngByAddOnId(@Param("addId") String addId);

    @Query(value = "SELECT * FROM option op INNER JOIN material_used mu ON op.option_id = mu.option_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.mate_id= :mateId ORDER BY op.option_name_th ASC", nativeQuery = true)
    List<Option> findOptionByMateId(String mateId);
}
