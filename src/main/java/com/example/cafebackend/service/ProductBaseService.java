package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.ProductBaseRepository;
import com.example.cafebackend.table.ProductBase;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductBaseService {

    private final ProductBaseRepository productBaseRepository;

    public ProductBaseService(ProductBaseRepository productBaseRepository) {
        this.productBaseRepository = productBaseRepository;
    }


    //////////////////////////////////////////////////////////
    public ProductBase createProductBase(String prodTitle) throws BaseException {
        /// verify
        if(productBaseRepository.existsByProdTitle(prodTitle)) throw ProductException.createFailTitleDuplicate();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "PB"+uuid.substring(0, 13);
        /// save
        ProductBase table = new ProductBase();
        table.setProdBaseId(uuid);
        table.setProdTitle(prodTitle);
        table.setImage("none");
        table.setIsEnable(true);
        table.setIsMaterialEnable(true);
        table.setDescription("none");
        return productBaseRepository.save(table);
    }
    //////////////////////////////////

    public ProductBase updateProductBase(ProductBase productBase) throws BaseException {
        /// verify
        if(Objects.isNull(productBase)) throw ProductException.findBaseFail();
        /// save
        return productBaseRepository.save(productBase);
    }
    //////////////////////////////////

    public Optional<ProductBase> findBaseById(String id){
        ///
        return productBaseRepository.findById(id);
    }
    //////////////////////////////////

    public List<ProductBase> findBaseAll(){
        ///
        return productBaseRepository.findAll();
    }
    //////////////////////////////////

    public List<ProductBase> findBaseByMateId(String mateId){
        ///
        return productBaseRepository.findProdBaseByMateId(mateId);
    }
    //////////////////////////////////

    public Boolean checkExistsByTitle(String title){
        /// validate
        return productBaseRepository.existsByProdTitle(title);
    }
    //////////////////////////////////

    public Boolean deleteProductBase(String id) throws BaseException {
        /// verify
        productBaseRepository.deleteById(id);
        Optional<ProductBase> prodOpt = productBaseRepository.findById(id);
        if(prodOpt.isEmpty()){
            return true;
        }
        throw ProductException.deleteFail();

    }
    //////////////////////////////////

}
