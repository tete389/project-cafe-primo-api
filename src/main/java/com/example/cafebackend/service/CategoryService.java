package com.example.cafebackend.service;

import com.example.cafebackend.repository.CategoryRepository;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    //////////////////////////

    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }

    /////////////////////////

    public List<Category> findAllCate() {
        return categoryRepository.findAll();
    }

    /////////////////////////

    public Category createCategory(String cateName, String cateStatus) {
        Category table = new Category();
        table.setCateName(cateName);
        table.setCateStatus(cateStatus);
        return categoryRepository.save(table);
    }

    /////////////////////////

    public Category updateCategory(Category cate, String cateName, String cateStatus) {
        cate.setCateName(cateName);
        cate.setCateStatus(cateStatus);
        return categoryRepository.save(cate);
    }

    /////////////////////////

    public void deleteCategory(Integer cateId) {
        categoryRepository.deleteById(cateId);
    }

    /////////////////////////
    public void addProductInCate(Category cate, List<Product> prod){

        cate.getProduct().addAll(prod);
        categoryRepository.save(cate);
    }

    /////////////////////////
    public void addCateInProd(Category cate, Product prod){

        cate.getProduct().add(prod);
        categoryRepository.save(cate);
    }

}
