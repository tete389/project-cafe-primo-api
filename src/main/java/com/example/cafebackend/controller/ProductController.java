package com.example.cafebackend.controller;

import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.request.*;
import com.example.cafebackend.model.response.ProductCustomerResponse;
import com.example.cafebackend.model.response.ProductResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductController {

    private ProductService productService;

    private CategoryService categoryService;

    private MaterialService materialService;

    private TypeService typeService;

    private AdditionalService additionalService;

    private ProductMapper productMapper;

    ////////////////////////////////////////////////

    public List<Product> getAllProd(){
        return productService.findAllProd();
    }

    ////////////////////////////////////////////////


    public Product getProdById(Product request){
        Optional<Product> product =  productService.findById(request.getProdId());
        return product.orElse(null);

    }

    ////////////////////////////////////////////////

    public String createProduct(ProdRequest request){
        Product product  = productService.createProduct(request.getProdName(), request.getProdImg(), request.getProdStatus(), request.getProdPrice(), request.getProdTimeProcess());

        for (Type type: request.getProdType()) {
            typeService.createType(type.getTypeName(), type.getTypeStatus(), type.getTypePrice(), product);
        }

        return "create complete";
    }

    ////////////////////////////////////////////////

    public String updateProduct(Product request){
        Optional <Product> product = productService.findById(request.getProdId());
        if(product.isEmpty()){
            return null; /// TODO
        }
        Product prod =  product.get();
        productService.updateProduct(prod, request.getProdName(), request.getProdImg(), request.getProdStatus(), request.getProdPrice(), request.getProdTimeProcess());
        return "update complete";
    }

    ////////////////////////////////////////////////

    public String deleteProd(Product request){
        productService.deleteProductById(request.getProdId());
        return "delete complete";
    }

    ////////////////////////////////////////

    public String addCategoryInProd(ProdCateRequest request){

        for (Integer i : request.getCateId()){
           Optional<Category> cate =  categoryService.findById(i);
           if(cate.isEmpty()){
               // TODO
               return "cate1";
           }
           for (Integer j : request.getProdId()){
            Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                // TODO
                return "cate2";
                }
                categoryService.addCateInProd(cate.get(), prod.get());
           }

        }
        return "complete";
    }

    ////////////////////////////////////////////////

    public String addMaterialInProd(ProdMateRequest request){

        for (Integer i : request.getMateId()){
            Optional<Material> mate =  materialService.findById(i);
            if(mate.isEmpty()){
                // TODO
                return "mate1";
            }
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "mate2";
                }
                materialService.addMateInProd(mate.get(), prod.get());
            }

        }
        return "complete";
    }

    ////////////////////////////////////////////////

    public String addTypeInProd(ProdTypeRequest request){

        for (Integer i : request.getTypeId()){
            Optional<Type> type =  typeService.findById(i);
            if(type.isEmpty()){
                // TODO
                return "cate1";
            }
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "cate2";
                }
                typeService.addTypeInProd(type.get(), prod.get());
            }

        }
        return "complete";
    }

    ////////////////////////////////////////////////

    public String addAddInProd(ProdAddRequest request){

        for (Integer i : request.getAddId()){
            Optional<Additional> add =  additionalService.findById(i);
            if(add.isEmpty()){
                // TODO
                return "cate1";
            }
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "cate2";
                }
                additionalService.addAddInProd(add.get(), prod.get());
            }

        }
        return "complete";
    }

    ////////////////////////////////////////////////

    public List<ProductCustomerResponse> getProdForCustomer(){
        List<Product> prodCus = productService.findAllProd();
        return  productMapper.toListProductCustomerResponse(prodCus);
    }

    ////////////////////////////////////////////////


    public List<ProductResponse> getProdByCateId(Category request){
        List<Product> prod =  productService.findProdByCateId(request.getCateId());

        return productMapper.toListProductResponse(prod);

    }

    ////////////////////////////////////////////////

    public List<ProductResponse> getProdByMateId(Material request){
        List<Product> prod =  productService.findProdByMateId(request.getMateId());
        return productMapper.toListProductResponse(prod);

    }

    ////////////////////////////////////////////////

//    public List<ProductResponse> getProdByTypeId(Type request){
//        List<Product> prod =  productService.findProdByTypeId(request.getTypeId());
//        return productMapper.toListProductResponse(prod);
//
//    }

    ////////////////////////////////////////////////

}
