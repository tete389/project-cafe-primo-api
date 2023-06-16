package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.mapper.CategoryMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForCategoryResponse;
import com.example.cafebackend.model.response.ForProductOnlyResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.CategoryService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.ProductForm;
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

    private ProductFormService productFormService;

    private CategoryMapper categoryMapper;

    private ProductMapper productMapper;

    //////////////////////////////////////////////////////////////////////

    public MessageResponse createCategory(String cateName) throws BaseException {
        /// validate
        if(Objects.isNull(cateName) || cateName.isEmpty()) throw CategoryException.createFail();
        Category cate = categoryService.createCategory(cateName);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Category success");
        res.setRes(cate);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse updateCategory(String cateId, String cateName, String isEnable) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();
        if(Objects.isNull(cateName) || cateName.isEmpty()) throw CategoryException.updateFail();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw CategoryException.updateFail();
        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if(Objects.isNull(category) || category.isEmpty()) throw CategoryException.findFail();
        Category cate =  category.get();
        /// check category name
        if(!cateName.equals(cate.getCateName())) {
            if(categoryService.existsByName(cateName)) throw CategoryException.updateFail();
            cate.setCateName(cateName);
        }
        /// check isEnable
        if(isEnable.equals("true")){
            if(cate.getIsEnable().equals(false)) cate.setIsEnable(true);
        } else if (isEnable.equals("false")) {
            if(cate.getIsEnable().equals(true)) cate.setIsEnable(false);
        }
        /// update category
        Category resCate = categoryService.updateCategory(cate);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Category success");
        res.setRes(resCate);
        return res;
    }

    public MessageResponse updateAddProduct(String cateId, List<String> formId) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();
        //if(Objects.isNull(formId) || formId.isEmpty()) throw CategoryException.updateFail();
        /// verify
        Optional<Category> cateOpt = categoryService.findById(cateId);
        if(Objects.isNull(cateOpt) || cateOpt.isEmpty()) throw CategoryException.findFail();
        Category category =  cateOpt.get();
        List<ProductForm> formList = new ArrayList<>();
        /// check product form
        if (!(Objects.isNull(formId) || formId.isEmpty())) {
            for (String form : formId) {
                Optional<ProductForm> formOpt = productFormService.findProductFormById(form);
                if (Objects.isNull(formOpt) || formOpt.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
                formList.add(formOpt.get());
            }
        }
        /// set product form
        category.getProductForm().clear();
        category.getProductForm().addAll(formList);
        /// response
        Category resCategory = categoryService.updateCategory(category);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Category success");
        res.setRes(resCategory);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findCategoryById(String cateId) throws BaseException {
        /// validate
        Optional<Category> cate =  categoryService.findById(cateId);
        if(cate.isEmpty()) throw CategoryException.findFail();
        MessageResponse res = new MessageResponse();
        res.setMessage("get Category By ID success");
        res.setRes(cate);
        return res;
    }

    public MessageResponse findCategoryAll()  {
        /// validate
        List<Category> cateList = categoryService.findListCategory();
        List<ForCategoryResponse> cateResList = new ArrayList<>();
        for (Category cate : cateList){
            List<ProductForm> prodList = productFormService.findProductByCateId(cate.getCateId());
            ForCategoryResponse cateRes = categoryMapper.toForCategoryResponse(cate, prodList);
            cateResList.add(cateRes);
        }
        MessageResponse res = new MessageResponse();
        res.setMessage("get all category");
        res.setRes(cateResList);
        return res;
    }
    ////////////////////////////////////////


    public MessageResponse findListProductByCategoryId(String cateId) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty())throw CategoryException.findFail();
        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if(category.isEmpty()) throw CategoryException.findFail();
        List<ProductForm> productForms =  productFormService.findProductByCateId(cateId);
        List<ForProductOnlyResponse> pdList = productMapper.toListProductFormOnlyResponse(productForms);
        MessageResponse res = new MessageResponse();
        res.setMessage("find ListProducts By Category ID");
        res.setRes(pdList);
        return res;
    }



    ////////////////////////////////////////////////
    public MessageResponse deleteCategory(String cateId) throws BaseException {
        Boolean cate =  categoryService.deleteCategory(cateId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Category success");
        res.setRes(cate);
        return res;
    }

    ////////////////////////////////////////

}
