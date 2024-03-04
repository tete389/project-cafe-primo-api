package com.example.cafebackend.repository;

import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseFormCountResponse;
import com.example.cafebackend.table.ProductBase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductBaseRepository extends JpaRepository<ProductBase, String> {

    @Query(value = "SELECT * FROM product_base pb INNER JOIN material_used mu ON pb.prod_base_id = mu.prod_base_id INNER JOIN material m ON mu.mate_id = m.mate_id WHERE mu.mate_id= :mateId AND pb.is_delete = false ORDER BY pb.prod_title_th ASC", nativeQuery = true)
    List<ProductBase> findProdBaseByMateId(@Param("mateId") String mateId);

    @Query(value = "SELECT * FROM product_base pb INNER JOIN cate_prod cp ON cp.prod_base_id = pb.prod_base_id INNER JOIN category c ON c.cate_id = cp.cate_id WHERE cp.cate_id= :cateId AND pb.is_delete = false ORDER BY pb.prod_title_th ASC", nativeQuery = true)
    Page<ProductBase> findProdBaseByCateId(String cateId, Pageable pageable);

    @Query(value = "SELECT * FROM product_base pb  WHERE pb.is_delete = false ORDER BY pb.prod_base_id ASC", nativeQuery = true)
    List<ProductBase> findProdBaseAll();

    @Query(value = "SELECT * FROM product_base pb  WHERE pb.is_delete = false ORDER BY pb.prod_base_id ASC", nativeQuery = true)
    Page<ProductBase> findProdBaseAllPageable(Pageable pageable);

    @Query(value = "SELECT pb.prod_base_id as prodBaseId , pb.prod_title_th as prodTitleTh , pb.prod_title_eng as prodTitleEng , pb.is_enable as isEnable , pb.is_material_enable as isMaterialEnable , pb.description as description , pb.image as image , count(pf.prod_form_id) as fromCount FROM product_base pb INNER JOIN product_form pf  ON pb.prod_base_id = pf.prod_base_id  WHERE pb.is_delete = false GROUP BY pb.prod_base_id order by pb.prod_base_id", nativeQuery = true)
    Page<ForProductBaseFormCountResponse> findProdBaseAndCountFromAllPageable(Pageable pageable);

    @Query(value = "SELECT pb.prod_base_id as prodBaseId , pb.prod_title_th as prodTitleTh , pb.prod_title_eng as prodTitleEng , pb.is_enable as isEnable , pb.is_material_enable as isMaterialEnable , pb.description as description , pb.image as image FROM product_base pb  WHERE pb.is_delete = false  order by pb.prod_base_id", nativeQuery = true)
    Page<ForProductBaseFormCountResponse> findProdBaseAndNoFromAllPageable(Pageable pageable);

    @Query(value = "SELECT * FROM product_base pb WHERE pb.prod_base_id= :prodId AND pb.is_delete = false", nativeQuery = true)
    Optional<ProductBase> findProdBaseById(@Param("prodId") String prodId);

    @Query(value = "SELECT pb.prod_title_th FROM product_base pb WHERE pb.prod_title_th= :title", nativeQuery = true)
    Optional<ProductBase> findByProdTitleTh(String title);

    boolean existsByProdTitleTh(String title);

    boolean existsByProdTitleEng(String title);

    @Query(value = "SELECT pb.is_delete FROM product_base pb WHERE pb.prod_title_th= :title", nativeQuery = true)
    boolean findProdBaseDeleteByTitleTh(@Param("title") String title);

    @Query(value = "SELECT pb.is_delete FROM product_base pb WHERE pb.prod_title_eng= :title", nativeQuery = true)
    boolean findProdBaseDeleteByTitleEng(@Param("title") String title);

    @Query(value = "SELECT pb.is_delete FROM product_base pb WHERE pb.prod_base_id= :prodId", nativeQuery = true)
    boolean findProdBaseDeleteById(@Param("prodId") String prodId);

}
