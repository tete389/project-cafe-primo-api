package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.repository.CategoryRepository;
import com.example.cafebackend.table.Category;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //////////////////////////
    public Category createCategory(String cateNameTh, String cateNameEng) throws BaseException {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "C" + uuid.substring(0, 14);
        /// save
        Category table = new Category();
        table.setCateId(uuid);
        table.setCateNameTh(cateNameTh);
        table.setCateNameEng(cateNameEng);
        table.setIsEnable(true);
        table.setIsRecommend(false);
        table.setIsDelete(false);
        return categoryRepository.save(table);
    }

    public Optional<Category> findById(String id) {
        ///
        return categoryRepository.findCategoryById(id);
    }

    public List<Category> findListCategoryAll() {
        ///
        return categoryRepository.findCategoryAll();
    }

    public List<Category> findListCategoryAllPageable(Pageable pageable) {
        ///
        return categoryRepository.findCategoryAllPagable(pageable).getContent();
    }

    // public List<Category> findListCategoryAllByNotRecommend() {
    // ///
    // return categoryRepository.findListCategoryAllByNotRecommend();
    // }

    public List<Category> findListCategoryAllByRecommendPageable(Pageable pageable) {
        ///
        return categoryRepository.findCategoryAllByRecommend(pageable).getContent();
    }

    public Optional<Category> findByNameTh(String cateName) {
        ///
        return categoryRepository.findByCateNameTh(cateName);
    }

    public Optional<Category> findByNameEng(String cateName) {
        ///
        return categoryRepository.findByCateNameEng(cateName);
    }

    public Category updateCategory(Category cate) throws BaseException {
        /// verify
        if (Objects.isNull(cate))
            throw CategoryException.updateFail();
        /// save
        return categoryRepository.save(cate);
    }

    public Boolean existsByNameTh(String title) {
        ///
        return categoryRepository.existsByCateNameTh(title);
    }

    public Boolean existsByNameEng(String title) {
        ///
        return categoryRepository.existsByCateNameEng(title);
    }

    public Boolean deleteCategory(String cateId) throws BaseException {
        /// verify
        categoryRepository.deleteById(cateId);
        Optional<Category> cate = categoryRepository.findById(cateId);
        if (cate.isEmpty()) {
            return true;
        }
        throw CategoryException.deleteFail();

        // Optional<Category> cate = categoryRepository.findById(cateId);
        // cate.get().setIsDelete(true);
        // cate.get().deleteCategory();
        // cate.get().setProductBase(null);
        // categoryRepository.save(cate.get());
        // return true;
    }
    /////////////////////////
    // public void deleteCategory(String cateId) throws BaseException {
    // Optional<Category> cateOpt = categoryRepository.findById(cateId);
    // if(cateOpt.isEmpty()){
    // throw CategoryException.deleteFail();
    // }
    // Category cate = cateOpt.get();
    // cate.setCateStatus("disable");
    // cate.setCateName("disable/"+cate.getCateName()+"/"+cate.getCateId());
    // categoryRepository.save(cate);
    // //categoryRepository.deleteById(cateId);
    // }

    /////////////////////////

}
