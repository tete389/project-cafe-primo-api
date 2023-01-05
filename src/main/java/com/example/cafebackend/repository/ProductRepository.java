package com.example.cafebackend.repository;

import com.example.cafebackend.table.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryCateId(Integer cateId);

    List<Product> findByMaterialMateId(Integer mateId);

//    List<Product> findByTypeTypeId(Integer mateId);




//    @Query(value = "SELECT pd.prod_id, pd.prod_name, pd.prod_img, pd.prod_status, pd.prod_price, " +
//            "pd.prod_time_process FROM product pd", nativeQuery = true)
//    List<productResponse> findProdAll();
//
//    public static interface productResponse{
//        String getProd_Id();
//
//        String getProd_Name();
//
//        String getProd_Img();
//
//        String getProd_Status();
//
//        String getProd_Price();
//
//        String getProd_Time_Process();
//
//    }


}
