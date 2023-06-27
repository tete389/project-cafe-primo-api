package com.example.cafebackend.repository;

import com.example.cafebackend.table.MaterialUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialUsedRepository extends JpaRepository<MaterialUsed, String> {

    @Query(value = "DELETE FROM material_used m WHERE m.prod_form_id= :formId", nativeQuery = true)
    void deleteByProdFormId(String formId);

    @Query(value = "SELECT * FROM material_used mu INNER JOIN product_base pb ON mu.prod_base_id = pb.prod_base_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.prod_base_id= :baseId AND mu.mate_id= :mateId ", nativeQuery = true)
    Optional<MaterialUsed> findByProdBaseIdAndMateId(String baseId, String mateId);

    @Query(value = "SELECT * FROM material_used mu INNER JOIN product_form pf ON mu.prod_form_id = pf.prod_form_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.prod_form_id= :formId AND mu.mate_id= :mateId ", nativeQuery = true)
    Optional<MaterialUsed> findByProdFormIdAndMateId(String formId, String mateId);

    @Query(value = "SELECT * FROM material_used mu INNER JOIN option op ON mu.option_id = op.option_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.option_id= :opId AND mu.mate_id= :mateId ", nativeQuery = true)
    Optional<MaterialUsed> findByOptionIdAndMateId(String opId, String mateId);
}
