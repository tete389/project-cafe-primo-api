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
    public ProductForm createProductForm(ProductBase prod, String prodForm, Double prodPrice, String description) throws BaseException {
        /// verify
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "PF"+uuid.substring(0, 13);
        /// save
        ProductForm table = new ProductForm();
        table.setProductBase(prod);
        table.setProdFormId(uuid);
        table.setProdForm(prodForm);
        table.setIsEnable(true);
        table.setIsMaterialEnable(true);
        table.setPrice(prodPrice);
        table.setDescription(description);
        return productFormRepository.save(table);
    }
    //////////////////////////////////

    public ProductForm updateProductForm(ProductForm prod) throws BaseException {
        /// verify
        if(Objects.isNull(prod)) throw ProductException.updateFailProductNull();
        /// save
        return productFormRepository.save(prod);

    }
    //////////////////////////////////////////////////////// checkExists

    public Boolean checkExistsByForm(String form){
        /// validate
        return productFormRepository.existsByProdForm(form);
    }
    ////////////////////////////////////////////////////////

    public List<ProductForm> findListProduct(){
        /// search
        return productFormRepository.findAllProduct();
    }

    public List<ProductForm> findProductFormByBaseId(String baseId){
        ///
        return productFormRepository.findProductFormByBaseId(baseId);
    }

    public List<String> findFormByBaseId(String baseId){
        ///
        return productFormRepository.findFormByBaseId(baseId);
    }

    public List<ProductForm> findFormByMateId(String mateId){
        ///
        return productFormRepository.findProdFormByMateId(mateId);
    }

    public Optional<ProductForm> findProductFormById(String prodId){
        ///
        return productFormRepository.findById(prodId);
    }

    public Double findProductMinPriceByBaseId(String prodId){
        ///
        return productFormRepository.findMinPrice(prodId);
    }
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
