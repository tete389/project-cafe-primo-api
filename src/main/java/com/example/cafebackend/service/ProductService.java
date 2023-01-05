package com.example.cafebackend.service;

import com.example.cafebackend.repository.ProductRepository;
import com.example.cafebackend.table.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //////////////////////////////////////////////////////////

    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

    //////////////////////////////////

    public List<Product> findAllProd(){
        return productRepository.findAll();
    }


    //////////////////////////////////
    public Product createProduct(String prodName, String prodImg, String prodStatus, double prodPrice, double prodTime) {
        Product table = new Product();
        table.setProdName(prodName);
        table.setProdImg(prodImg);
        table.setProdStatus(prodStatus);
        table.setProdPrice(prodPrice);
        table.setProdTimeProcess(prodTime);
        return productRepository.save(table);
    }

    //////////////////////////////////

    public Product updateProduct(Product prod, String prodName, String prodImg, String prodStatus, double prodPrice, double prodTime) {
        prod.setProdName(prodName);
        prod.setProdImg(prodImg);
        prod.setProdStatus(prodStatus);
        prod.setProdPrice(prodPrice);
        prod.setProdTimeProcess(prodTime);
        return productRepository.save(prod);
    }

    //////////////////////////////////

    public void deleteProductById(Integer prodId) {
        productRepository.deleteById(prodId);
    }

    //////////////////////////////////

    public List<Product> findProdByCateId(Integer cateId){

        return productRepository.findByCategoryCateId(cateId);
    }

    //////////////////////////////////

//    public List<Product> findProdByTypeId(Integer typeId){
//        return productRepository.findByTypeTypeId(typeId);
//    }

    //////////////////////////////////

    public List<Product> findProdByMateId(Integer mateId){
        return productRepository.findByMaterialMateId(mateId);
    }

    //////////////////////////////////

//    public void addCategory(Product prod, List<Category> cate){
//
//        //prod.getCategory().add(cate);
//        prod.setCategory(cate);
//        prod.setProdName("upp");
//        productRepository.save(prod);
//    }



}
