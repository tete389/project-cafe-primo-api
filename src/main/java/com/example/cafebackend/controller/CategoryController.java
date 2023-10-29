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
import com.example.cafebackend.service.ProductBaseService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.ProductBase;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private ProductBaseService productBaseService;

    private CategoryMapper categoryMapper;

    private ProductMapper productMapper;

    //////////////////////////////////////////////////////////////////////

    public MessageResponse createCategory(String cateNameTh, String cateNameEng) throws BaseException {
        /// validate
        if (Objects.isNull(cateNameTh) || cateNameTh.isEmpty())
            throw CategoryException.createFail();
        if (Objects.isNull(cateNameEng) || cateNameEng.isEmpty())
            cateNameEng = "none";
        /// verify
        if (categoryService.existsByNameTh(cateNameTh))
            throw CategoryException.createFail();
        if (categoryService.existsByNameEng(cateNameEng)) {
            if (!cateNameEng.equals("none")) {
                throw CategoryException.createFail();
            }
        }
        Category cate = categoryService.createCategory(cateNameTh, cateNameEng);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Category success");
        res.setRes(cate);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse updateCategory(String cateId, String cateNameTh, String cateNameEng, Boolean isEnable,
            Boolean isRecommend)
            throws BaseException {
        /// validate
        if (Objects.isNull(cateId) || cateId.isEmpty())
            throw CategoryException.updateFail();

        /// verify
        Optional<Category> category = categoryService.findById(cateId);
        if (Objects.isNull(category) || category.isEmpty())
            throw CategoryException.findFail();
        Category cate = category.get();
        /// check category name
        if (!(Objects.isNull(cateNameTh) || cateNameTh.isEmpty())) {
            if (!cateNameTh.equals(cate.getCateNameTh())) {
                if (categoryService.existsByNameTh(cateNameTh))
                    throw CategoryException.updateFail();
                cate.setCateNameTh(cateNameTh);
            }
        }
        if (!(Objects.isNull(cateNameEng) || cateNameEng.isEmpty())) {
            if (!cateNameEng.equals(cate.getCateNameEng())) {
                if (categoryService.existsByNameEng(cateNameEng))
                    throw CategoryException.updateFail();
                cate.setCateNameEng(cateNameEng);
            }
        }

        /// check isEnable
        // String enable = String.valueOf(cate.getIsEnable());
        if (!(Objects.isNull(isEnable))) {
            if (!isEnable.equals(cate.getIsEnable())) {
                cate.setIsEnable(isEnable);
            }
        }

        /// check isRecommend
        // String recommend = String.valueOf(cate.getIsRecommend());
        if (!(Objects.isNull(isRecommend))) {
            if (!isRecommend.equals(cate.getIsRecommend())) {
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

    private MessageResponse findCategoryById(String cateId, String haveBase, String minPrice) throws BaseException {
        Optional<Category> category = categoryService.findById(cateId);
        if (category.isEmpty())
            throw CategoryException.findFail();
        Category cate = category.get();
        /// have base true
        if (!(Objects.isNull(haveBase) || haveBase.isEmpty())) {
            if (haveBase.equals("true")) {
                /// have min price
                if (!(Objects.isNull(minPrice) || minPrice.isEmpty())) {
                    if (minPrice.equals("true")) {
                        List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                        for (ProductBase productBase : cate.getProductBase()) {
                            Double min_price = productFormService
                                    .findProductMinPriceByBaseId(productBase.getProdBaseId());
                            ForProductBaseMinPriceResponse prodMinPrice = productMapper
                                    .toForProductBaseMinPriceResponse(productBase, min_price);
                            list.add(prodMinPrice);
                        }
                        ForCategoryProductBaseMinPriceResponse cateRes = categoryMapper
                                .toForCategoryProductInfoPriceResponse(cate, list);
                        /// response
                        MessageResponse res = new MessageResponse();
                        res.setMessage("get category list base price By ID");
                        res.setRes(cateRes);
                        return res;
                    }
                }
                ForCategoryResponse cteRes = categoryMapper.toForCategoryResponse(cate);
                /// response
                MessageResponse res = new MessageResponse();
                res.setMessage("get Category list base By ID");
                res.setRes(cteRes);
                return res;
            }

        }
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get Category By ID");
        res.setRes(cate);
        return res;
    }

    ////////////////////////////////////////

    public MessageResponse findCategory(String cateId, String haveBase, String minPrice, Integer pageSize,
            Integer pageNum) throws BaseException {
        /// validate
        if (!(Objects.isNull(cateId) || cateId.isEmpty())) {
            MessageResponse resById = findCategoryById(cateId, haveBase, minPrice);
            return resById;
        }

        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 30;
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize);

        //// set Recommend
        List<Category> cateList = categoryService.findListCategoryAllByRecommendPageable(pageable);
        /// set response
        /// have base
        if (!(Objects.isNull(haveBase) || haveBase.isEmpty()) && haveBase.equals("true")) {
            /// have min price
            if (!(Objects.isNull(minPrice) || minPrice.isEmpty()) && minPrice.equals("true")) {
                List<ForCategoryProductBaseMinPriceResponse> cateRes = new ArrayList<>();
                cateList.forEach(findCate -> {
                    List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                    productBaseService.findBaseAllASCByCateIdPageable(findCate.getCateId(),
                            pageable).forEach(findBase -> {
                                Double min_price = productFormService
                                        .findProductMinPriceByBaseId(findBase.getProdBaseId());
                                ForProductBaseMinPriceResponse prodMinPrice = productMapper
                                        .toForProductBaseMinPriceResponse(findBase, min_price);
                                list.add(prodMinPrice);
                            });
                    // for (ProductBase productBase :
                    // productBaseService.findBaseAllASCByCateIdPageable(cateId,
                    // pageable)) {
                    // Double min_price =
                    // productFormService.findProductMinPriceByBaseId(productBase.getProdBaseId());
                    // ForProductBaseMinPriceResponse prodMinPrice = productMapper
                    // .toForProductBaseMinPriceResponse(productBase, min_price);
                    // list.add(prodMinPrice);
                    // }
                    ForCategoryProductBaseMinPriceResponse cate = categoryMapper
                            .toForCategoryProductInfoPriceResponse(findCate, list);
                    cateRes.add(cate);
                });
                // for (Category category : cateList) {
                // List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                // for (ProductBase productBase :
                // productBaseService.findBaseAllASCByCateIdPageable(cateId,
                // pageable)) {
                // Double min_price =
                // productFormService.findProductMinPriceByBaseId(productBase.getProdBaseId());
                // ForProductBaseMinPriceResponse prodMinPrice = productMapper
                // .toForProductBaseMinPriceResponse(productBase, min_price);
                // list.add(prodMinPrice);
                // }
                // ForCategoryProductBaseMinPriceResponse cate = categoryMapper
                // .toForCategoryProductInfoPriceResponse(category, list);
                // cateRes.add(cate);
                // }
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

    ////////////////////////////////////////

    ////////////////////////////////////////////////

    public MessageResponse deleteCategory(String cateId) throws BaseException {
        /// validate
        if (Objects.isNull(cateId) || cateId.isEmpty())
            throw CategoryException.findFail();
        Boolean cate = categoryService.deleteCategory(cateId);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Category success");
        res.setRes(cate);
        return res;
    }
    ////////////////////////////////////////

}
