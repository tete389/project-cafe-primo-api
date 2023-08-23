package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.repository.CategoryRepository;
import com.example.cafebackend.table.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    //////////////////////////
    public Category createCategory(String cateName) throws BaseException {
        /// verify
        if(categoryRepository.existsByCateName(cateName)) throw CategoryException.createFail();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "C"+uuid.substring(0, 14);
        /// save
        Category table = new Category();
        table.setCateId(uuid);
        table.setCateName(cateName);
        table.setIsEnable(true);
        table.setIsRecommend(false);
        return categoryRepository.save(table);
    }

    public Optional<Category> findById(String id){
        ///
        return categoryRepository.findById(id);
    }

    public List<Category> findListCategoryAll() {
        ///
        return categoryRepository.findListCategoryAll();
    }

    public List<Category> findListCategoryAllByNotRecommend() {
        ///
        return categoryRepository.findListCategoryAllByNotRecommend();
    }

    public List<Category> findListCategoryAllByRecommend() {
        ///
        return categoryRepository.findListCategoryAllByRecommend();
    }

    public Optional<Category> findByName(String cateName) {
        ///
        return categoryRepository.findByCateName(cateName);
    }

    public Category updateCategory(Category cate) throws BaseException {
        /// verify
        if(Objects.isNull(cate)) throw CategoryException.updateFail();
        /// save
        return categoryRepository.save(cate);
    }

    public Boolean existsByName(String title) {
        ///
        return categoryRepository.existsByCateName(title);
    }

    public Boolean deleteCategory(String cateId) throws BaseException {
        /// verify
        categoryRepository.deleteById(cateId);
        Optional<Category> cate = categoryRepository.findById(cateId);
        if(cate.isEmpty()) return true;
        throw CategoryException.deleteFail();
    }
    /////////////////////////
//    public void deleteCategory(String cateId) throws BaseException {
//        Optional<Category> cateOpt = categoryRepository.findById(cateId);
//        if(cateOpt.isEmpty()){
//            throw CategoryException.deleteFail();
//        }
//        Category cate = cateOpt.get();
//        cate.setCateStatus("disable");
//        cate.setCateName("disable/"+cate.getCateName()+"/"+cate.getCateId());
//        categoryRepository.save(cate);
//        //categoryRepository.deleteById(cateId);
//    }

    /////////////////////////

}
