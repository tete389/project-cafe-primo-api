package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.ProductRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    //////////////////////////////////////////////////////////
    public Product createProduct(BaseProduct prod, String prodForm, String prodName, Double prodPrice) throws BaseException {
        /// verify
        if(productRepository.existsByProdName(prodName)) throw ProductException.createFailFormDuplicate();
        /// create id
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        String n1 = String.valueOf(1000 + now.get(Calendar.SECOND) * now.get(Calendar.MINUTE));
        String n2 = String.valueOf(100 + now.get(Calendar.SECOND)+ now.get(Calendar.MINUTE));
        String Id = "PD"+n1+n2;
        /// save
        Product table = new Product();
        table.setBaseProduct(prod);
        table.setProdId(Id);
        table.setProdName(prodName);
        table.setProdForm(prodForm);
        table.setIsEnable(true);
        table.setIsForSale(true);
        table.setPrice(prodPrice);
        table.setImage("none");
        table.setBonusPoint(0.0);
        table.setDescription("none");
        return productRepository.save(table);
    }


    //////////////////////////////////
    public List<Product> findListProduct(){
        /// search
        return productRepository.findAllProduct();
    }

    //////////////////////////////////////////////////////// update
    public Product updateProduct(Product prod) throws BaseException {
        /// verify
        if(Objects.isNull(prod)) throw ProductException.updateFailProductNull();
        /// save
        return productRepository.save(prod);
    }

    //////////////////////////////////////////////////////// checkExists
    public Boolean checkExistsByCategory(String cateId){
        /// validate
        return productRepository.existsByCategoryCateId(cateId);
    }
    public Boolean checkExistsByAddOn(String addId){
        /// validate
        return productRepository.existsByAddOnAddOnId(addId);
    }
    public Boolean checkExistsByMaterial(String mateId){
        /// validate
        return productRepository.existsByIngredientsIdMateId(mateId);
    }

    public Boolean checkExistsByName(String name){
        /// validate
        return productRepository.existsByProdName(name);
    }



    //////////////////////////////////////////////////////// find Product By **
    public List<Product> findProductByBaseId(String baseId){
        ///
        return productRepository.findByProductBaseId(baseId);
    }

    public List<Product> findProductByCateId(String cateId){
        ///
        return productRepository.findByCategoryCateId(cateId);
    }

    public Optional<Product> findProductById(String prodId){
        ///
        return productRepository.findById(prodId);
    }

    public Optional<Product> findProductByIdIsEnable(String prodId){
        ///
        return productRepository.findByProductId(prodId);
    }

    public List<Product> findProductByMaterialId(String mateId){
        ///
        return productRepository.findByIngredientsIdMateId(mateId);
    }

    public List<Product> findProductByAddOnlId(String mateId){
        ///
        return productRepository.findByIngredientsIdMateId(mateId);
    }

    ////////////////////////////////////////////////////////

    public Boolean deleteProduct(String id) throws BaseException {
        /// verify
        productRepository.deleteById(id);
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) return true;
        throw ProductException.deleteFail();
    }

    //////////////////////////////////

//    public void deleteProductById(String prodId) throws ProductException {
//        Optional<ProductDetail> prodOpt = productDetailRepository.findById(prodId);
//        if(prodOpt.isEmpty()){
//            throw ProductException.deleteFail();
//        }
//        ProductDetail prod = prodOpt.get();
//        prod.setProdStatus("disable");
//        prod.setProdName("disable/"+prod.getProdName()+"/"+prod.getProdId());
//        productDetailRepository.save(prod);
//        //productRepository.deleteById(prodId);
//    }





}
