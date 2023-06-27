package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.ProductFormRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductFormService {

    private final ProductFormRepository productFormRepository;

    public ProductFormService(ProductFormRepository productFormRepository) {
        this.productFormRepository = productFormRepository;

    }

    //////////////////////////////////////////////////////////
    public ProductForm createProductForm(ProductBase prod, String prodForm, Double prodPrice, Double bonusPoint, String description) throws BaseException {
        /// verify
        //if(productFormRepository.existsByProdName(prodName)) throw ProductException.createFailFormDuplicate();
        /// create id
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        String n1 = String.valueOf(1000 + now.get(Calendar.SECOND) * now.get(Calendar.MINUTE));
        String n2 = String.valueOf(100 + now.get(Calendar.SECOND)+ now.get(Calendar.MINUTE));
        String Id = "PF"+n1+n2;
        /// save
        ProductForm table = new ProductForm();
        table.setProductBase(prod);
        table.setProdFormId(Id);
        table.setProdForm(prodForm);
        table.setIsEnable(true);
        table.setPrice(prodPrice);
        table.setImage("none");
        table.setBonusPoint(bonusPoint);
        table.setDescription(description);
        return productFormRepository.save(table);
    }
    //////////////////////////////////


    public ProductForm updateProductForm(ProductForm prod) throws Exception {
        try {
            /// verify
            if(Objects.isNull(prod)) throw ProductException.updateFailProductNull();
            /// save
            return productFormRepository.save(prod);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //////////////////////////////////////////////////////// checkExists
    public Boolean checkExistsByCategory(String cateId){
        /// validate
        return productFormRepository.existsByCategoryCateId(cateId);
    }
    public Boolean checkExistsByAddOn(String addId){
        /// validate
        return productFormRepository.existsByAddOnAddOnId(addId);
    }
    public Boolean checkExistsByMaterialId(String mateId){
        /// validate
        return productFormRepository.existsByMaterialUsedMaterialMateId(mateId);
    }

    public Boolean checkExistsByForm(String form){
        /// validate
        return productFormRepository.existsByProdForm(form);
    }
    ////////////////////////////////////////////////////////

    public List<ProductForm> findListProduct(){
        /// search
        return productFormRepository.findAllProduct();
    }

    public List<ProductForm> findFormByBaseId(String baseId){
        ///
        return productFormRepository.findFormByBaseId(baseId);
    }

//    public List<ProductForm> findProductByCateId(String cateId){
//        ///
//        return productFormRepository.findByCategoryCateId(cateId);
//    }

    public Optional<ProductForm> findProductFormById(String prodId){
        ///
        return productFormRepository.findById(prodId);
    }


//    public List<ProductForm> findProductByMaterialId(String mateId){
//        ///
//        return productFormRepository.findByIngredientsIdMateId(mateId);
//    }

//    public List<ProductForm> findProductByAddOnlId(String mateId){
//        ///
//        return productFormRepository.findByIngredientsIdMateId(mateId);
//    }

    ////////////////////////////////////////////////////////

    public Boolean deleteFormById(String id) throws BaseException {
        /// verify
        productFormRepository.deleteById(id);
        Optional<ProductForm> product = productFormRepository.findById(id);
        if(product.isEmpty()) return true;
        throw ProductException.deleteFail();
    }

    //////////////////////////////////

}
