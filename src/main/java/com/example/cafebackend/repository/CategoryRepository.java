package com.example.cafebackend.repository;

import com.example.cafebackend.table.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsByCateNameTh(String name);

    boolean existsByCateNameEng(String name);

    @Query(value = "SELECT * FROM category c  WHERE c.cate_name_th =:name AND c.is_delete = false", nativeQuery = true)
    Optional<Category> findByCateNameTh(@Param("name") String name);

    @Query(value = "SELECT * FROM category c  WHERE c.cate_name_eng =:name AND c.is_delete = false", nativeQuery = true)
    Optional<Category> findByCateNameEng(@Param("name") String name);

    @Query(value = "SELECT * FROM category c WHERE c.cate_id= :cateId AND c.is_delete = false", nativeQuery = true)
    Optional<Category> findCategoryById(@Param("cateId") String cateId);

    @Query(value = "SELECT * FROM category c WHERE c.is_delete = false ORDER BY c.cate_name_th ASC ", nativeQuery = true)
    List<Category> findCategoryAll();

    @Query(value = "SELECT * FROM category c WHERE c.is_delete = false ORDER BY c.cate_name_th ASC ", nativeQuery = true)
    Page<Category> findCategoryAllPagable(Pageable pageable);

    // @Query(value = "SELECT * FROM category c WHERE c.is_recommend = false ORDER BY c.cate_name ASC WHERE c.is_delete = false", nativeQuery = true)
    // List<Category> findCategoryAllByNotRecommend();

    @Query(value = "SELECT * FROM category c WHERE c.is_delete = false ORDER BY c.is_recommend DESC, c.cate_name_th ASC", nativeQuery = true)
    Page<Category> findCategoryAllByRecommend(Pageable pageable);

}
