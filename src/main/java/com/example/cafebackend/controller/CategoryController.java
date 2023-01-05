package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.ProdCateRequest;
import com.example.cafebackend.service.CategoryService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryController {

    private CategoryService categoryService;

    private ProductService productService;

    //////////////////////////////////////////////////////////////////////

    public String createCategory(Category request) {
        categoryService.createCategory(request.getCateName(), request.getCateStatus());
        return "create complete";
    }

    ////////////////////////////////////////

    public List<Category> getAllCate(){
        return categoryService.findAllCate();
    }

    ////////////////////////////////////////

    public Category getCateById(Category request){
        Optional<Category> cate =  categoryService.findById(request.getCateId());
        return cate.orElse(null);
    }

    ////////////////////////////////////////

    public String updateCate(Category request){
        Optional<Category> Cate =  categoryService.findById(request.getCateId());
        if(Cate.isEmpty()){
            return null; /// TODO
        }
        Category category = Cate.get();
        categoryService.updateCategory(category, request.getCateName(), request.getCateName());
        return "update complete";
    }

    ////////////////////////////////////////

    public String deleteCate(Category request){
        categoryService.deleteCategory(request.getCateId());
        return "delete complete";
    }

    ////////////////////////////////////////

    public String addProductInCate(ProdCateRequest request){

        for (Integer i : request.getCateId()){
            Optional<Category> cate =  categoryService.findById(i);
            if(cate.isEmpty()){
                // TODO
                return "cate1";
            }

            List<Product> prodList = new ArrayList<>();
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "cate2";
                }
                prodList.add(prod.get());
            }
            categoryService.addProductInCate(cate.get(), prodList);
        }
        return "complete";
    }

}
