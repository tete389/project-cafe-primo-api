package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.mapper.CategoryMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForFindCategory.ForCategoryProductBaseMinPriceResponse;
import com.example.cafebackend.model.response.ForFindCategory.ForCategoryResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.CategoryService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.ProductBase;
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

    public MessageResponse updateCategory(String cateId, String cateName, Boolean isEnable, Boolean isRecommend) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();

        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if(Objects.isNull(category) || category.isEmpty()) throw CategoryException.findFail();
        Category cate =  category.get();
        /// check category name
        if (!(Objects.isNull(cateName) || cateName.isEmpty())) {
            if(!cateName.equals(cate.getCateName())) {
                if(categoryService.existsByName(cateName)) throw CategoryException.updateFail();
                cate.setCateName(cateName);
            }
        }

        /// check isEnable
        //String enable = String.valueOf(cate.getIsEnable());
        if (!(Objects.isNull(isEnable))) {
            if(!isEnable.equals(cate.getIsEnable())){
                cate.setIsEnable(isEnable);
            }
        }

        /// check isRecommend
        //String recommend = String.valueOf(cate.getIsRecommend());
        if (!(Objects.isNull(isRecommend))) {
            if(!isRecommend.equals(cate.getIsRecommend())){
                cate.setIsRecommend(isRecommend);
            }
        }
        /// update category
        Category resCate = categoryService.updateCategory(cate);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Category success");
        res.setRes(resCate);
        return res;
    }
    ////////////////////////////////////////

//    public MessageResponse updateProductFormInCategory(String cateId, List<String> formId) throws BaseException {
//        /// validate
//        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();
//        /// verify
//        Optional<Category> cateOpt = categoryService.findById(cateId);
//        if(Objects.isNull(cateOpt) || cateOpt.isEmpty()) throw CategoryException.findFail();
//        Category category =  cateOpt.get();
//        List<ProductForm> formList = new ArrayList<>();
//        /// check product form
//        if (!(Objects.isNull(formId) || formId.isEmpty())) {
//            for (String form : formId) {
//                Optional<ProductForm> formOpt = productFormService.findProductFormById(form);
//                if (Objects.isNull(formOpt) || formOpt.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
//                formList.add(formOpt.get());
//            }
//        }
//        /// set product form
//        category.getProductForm().clear();
//        category.getProductForm().addAll(formList);
//        /// response
//        Category resCategory = categoryService.updateCategory(category);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update Category success");
//        res.setRes(resCategory);
//        return res;
//    }
    ////////////////////////////////////////



    public MessageResponse findCategoryById(String cateId, String base, String minPrice) throws BaseException {
        /// validate
        if(!(Objects.isNull(cateId) || cateId.isEmpty())) {
            Optional<Category> category =  categoryService.findById(cateId);
            if(category.isEmpty()) throw CategoryException.findFail();
            Category cate = category.get();
            /// have base true
            if (!(Objects.isNull(base) || base.isEmpty()) && base.equals("true")) {
                /// have min price
                if (!(Objects.isNull(minPrice) || minPrice.isEmpty()) && minPrice.equals("true")) {
                    List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                    for (ProductBase productBase : cate.getProductBase()){
                        Double min_price = productFormService.findProductMinPriceByBaseId(productBase.getProdBaseId());
                        ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(productBase, min_price);
                        list.add(prodMinPrice);
                    }
                    ForCategoryProductBaseMinPriceResponse cateRes = categoryMapper.toForCategoryProductInfoPriceResponse(cate, list);
                    /// response
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get category list base price By ID");
                    res.setRes(cateRes);
                    return res;
                }
                ForCategoryResponse cteRes = categoryMapper.toForCategoryResponse(cate);
                /// response
                MessageResponse res = new MessageResponse();
                res.setMessage("get Category list base By ID");
                res.setRes(cteRes);
                return res;
            }
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage("get Category By ID");
            res.setRes(cate);
            return res;
        }
        //// set Recommend
        List<Category> cateList = categoryService.findListCategoryAllByRecommend();
        cateList.addAll(categoryService.findListCategoryAllByNotRecommend());
        /// set response
        /// have base
        if (!(Objects.isNull(base) || base.isEmpty()) && base.equals("true")) {
            /// have min price
            if (!(Objects.isNull(minPrice) || minPrice.isEmpty()) && minPrice.equals("true")) {
                List<ForCategoryProductBaseMinPriceResponse> cateRes = new ArrayList<>();
                for (Category category : cateList) {
                    List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                    for (ProductBase productBase : category.getProductBase()){
                        Double min_price = productFormService.findProductMinPriceByBaseId(productBase.getProdBaseId());
                        ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(productBase, min_price);
                        list.add(prodMinPrice);
                    }
                    ForCategoryProductBaseMinPriceResponse cate = categoryMapper.toForCategoryProductInfoPriceResponse(category, list);
                    cateRes.add(cate);
                }
                /// response
                MessageResponse res = new MessageResponse();
                res.setMessage("get all category list base price");
                res.setRes(cateRes);
                return res;
            }
            List<ForCategoryResponse> cteRes = categoryMapper.toListForCategoryResponse(cateList);
            /// response
            MessageResponse res = new MessageResponse();
            res.setMessage("get all Category list base By ID");
            res.setRes(cteRes);
            return res;

        }
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get all category");
        res.setRes(cateList);
        return res;
    }


//    public MessageResponse findCategoryAll()  {
//        /// validate
//        List<Category> cateList = categoryService.findListCategoryAllByRecommend();
//        cateList.addAll(categoryService.findListCategoryAllByNotRecommend());
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get all category");
//        res.setRes(cateList);
//        return res;
//    }
    ////////////////////////////////////////

//    public MessageResponse findCategoryAllListProductBaseInfoPrice() throws BaseException {
//        /// validate
//        List<Category> cateList = categoryService.findListCategoryAllByRecommend();
//        cateList.addAll(categoryService.findListCategoryAllByNotRecommend());
//        /// set response
//        List<ForCategoryProductBaseMinPriceResponse> cateRes = new ArrayList<>();
//        for (Category category : cateList) {
//            List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
//            for (ProductBase productBase : category.getProductBase()){
//                Double minPrice = productFormService.findProductMinPriceByBaseId(productBase.getProdBaseId());
//                List<Boolean> listMateUsedEnable = materialUsedService.findEnableByBaseId(productBase.getProdBaseId());
//                ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(productBase, minPrice, listMateUsedEnable);
//                list.add(prodMinPrice);
//            }
//            ForCategoryProductBaseMinPriceResponse cate = categoryMapper.toForCategoryProductInfoPriceResponse(category, list);
//            cateRes.add(cate);
//        }
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get all category list Products(B) Info price");
//        res.setRes(cateRes);
//        return res;
//    }
    ////////////////////////////////////////


//    public MessageResponse findListProductBaseByCategoryAll() throws BaseException {
//        /// verify
//        List<Category> cateList = categoryService.findListCategoryAllByRecommend();
//        cateList.addAll(categoryService.findListCategoryAllByNotRecommend());
//
//        /// set response
//        List<ForProdBaseOfCategoryResponse> cateRes = categoryMapper.toListForProdBaseOfCategoryResponse(cateList);
//
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Products(B) By All Category");
//        res.setRes(cateRes);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse findListProductBaseInfoByCategoryId(String cateId) throws BaseException {
//        /// validate
//        if(Objects.isNull(cateId) || cateId.isEmpty())throw CategoryException.findFail();
//        /// verify
//        Optional<Category> cateOpt = categoryService.findById(cateId);
//        if(cateOpt.isEmpty()) throw CategoryException.findFail();
//        Category category = cateOpt.get();
//        /// set response
//        List<ForFindMateEnableInPBResponse> formRes =  productMapper.toListForFindMateEnableInPBResponse(category.getProductBase());
//        //ForCategoryAndProdBaseInfoResponse cateRes = categoryMapper.toForCategoryAndProdBaseInfoResponse(category, formRes);
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Products(B) Info By Category ID");
//        res.setRes(formRes);
//        return res;
//    }


   ////////////////////////////////////////////////


    public MessageResponse deleteCategory(String cateId) throws BaseException {
        Boolean cate =  categoryService.deleteCategory(cateId);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Category success");
        res.setRes(cate);
        return res;
    }
    ////////////////////////////////////////

}
