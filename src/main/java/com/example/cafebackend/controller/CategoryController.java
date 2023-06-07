package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.mapper.CategoryMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForCategoryResponse;
import com.example.cafebackend.model.response.ForProductOnlyResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.CategoryService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryController {

    private CategoryService categoryService;

    private ProductService productService;

    private CategoryMapper categoryMapper;

    private ProductMapper productMapper;

    //////////////////////////////////////////////////////////////////////
    public MessageResponse createCategory(String cateName) throws BaseException {
        /// validate
        if(Objects.isNull(cateName) || cateName.isEmpty()) throw CategoryException.createFail();
        Category cate = categoryService.createCategory(cateName);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Category complete");
        res.setRes(cate);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse findCategoryById(String cateId) throws BaseException {
        /// validate
        Optional<Category> cate =  categoryService.findById(cateId);
        if(cate.isEmpty()) throw CategoryException.findFail();
        MessageResponse res = new MessageResponse();
        res.setMessage("get Category By ID complete");
        res.setRes(cate);
        return res;
    }

    public MessageResponse findCategoryAll()  {
        /// validate
        List<Category> cateList = categoryService.findListCategory();
        List<ForCategoryResponse> cateResList = new ArrayList<>();
        for (Category cate : cateList){
            List<Product> prodList = productService.findProductByCateId(cate.getCateId());
            ForCategoryResponse cateRes = categoryMapper.toForCategoryResponse(cate, prodList);
            cateResList.add(cateRes);
        }
        MessageResponse res = new MessageResponse();
        res.setMessage("get all category ");
        res.setRes(cateResList);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse setCategoryName(String cateId, String cateName) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();
        if(Objects.isNull(cateName) || cateName.isEmpty()) throw CategoryException.updateFail();
        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if(Objects.isNull(category) || category.isEmpty()) throw CategoryException.findFail();
        if(categoryService.existsByName(cateName)) throw CategoryException.updateFail();
        category.get().setCateName(cateName);
        Category cate = categoryService.updateCategory(category.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("set Name Category complete");
        res.setRes(cate);
        return res;
    }

    public MessageResponse setEnableCategory(String cateId, Boolean enable) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();
        if(Objects.isNull(enable) ) throw CategoryException.updateFail();
        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if(Objects.isNull(category) || category.isEmpty()) throw CategoryException.findFail();
        category.get().setIsEnable(enable);
        Category cate = categoryService.updateCategory(category.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("set enable complete");
        res.setRes(cate);
        return res;
    }



    ////////////////////////////////////////


    public MessageResponse findListProductByCategoryId(String cateId) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty())throw CategoryException.findFail();
        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if(category.isEmpty()) throw CategoryException.findFail();
        List<Product> products =  productService.findProductByCateId(cateId);
        List<ForProductOnlyResponse> pdList = productMapper.toListProductOnlyResponse(products);
        MessageResponse res = new MessageResponse();
        res.setMessage("find ListProducts By Category ID");
        res.setRes(pdList);
        return res;
    }



    ////////////////////////////////////////////////
    public MessageResponse deleteCategory(String cateId) throws BaseException {
        Boolean cate =  categoryService.deleteCategory(cateId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Category complete");
        res.setRes(cate);
        return res;
    }

    ////////////////////////////////////////

}
