package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.exception.FileException;
import com.example.cafebackend.exception.ProductException;

import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceFormResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@AllArgsConstructor
@Service
public class ProductBaseController {

    private ProductBaseService productBaseService;

    private ProductFormService productFormService;

    private CategoryService categoryService;

    private ProductMapper productMapper;



    private FileService fileService;

    ////////////////////////////////////////////////

    public MessageResponse createProductBase(String prodTitle) throws BaseException{
        /// validate
        if(Objects.isNull(prodTitle) ||  prodTitle.isEmpty()) throw ProductException.createBaseFail();
        /// verify
        ProductBase productBase = productBaseService.createProductBase(prodTitle);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("create Product(B) success");
        res.setRes(productBase);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateProductBase(String baseId, String prodTitle, String description, Boolean isEnable) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) ||  baseId.isEmpty()) throw ProductException.updateFailProductNull();
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if(productBase.isEmpty()) throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check prod title
        if (!(Objects.isNull(prodTitle) ||  prodTitle.isEmpty())){
            if(!prodTitle.equals(base.getProdTitle())) {
                if(productBaseService.checkExistsByTitle(prodTitle)) throw ProductException.updateFailTitleDuplicate();
                base.setProdTitle(prodTitle);
            }
        }
        /// check description
        if (!(Objects.isNull(description) ||  description.isEmpty())) {
            if(!description.equals(base.getDescription())) {
                base.setDescription(description);
            }
        }
        /// check isEnable
        //String enable = String.valueOf(base.getIsEnable());
        if (!(Objects.isNull(isEnable))) {
            if(!isEnable.equals(base.getIsEnable())){
                base.setIsEnable(isEnable);
            }
        }
        /// update product base
        ProductBase resBase =  productBaseService.updateProductBase(base);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Product(B) success");
        res.setRes(resBase);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateProductBaseIntoCategory(String cateId, List<ProductBase> baseId) throws BaseException {
        /// validate
        if(Objects.isNull(cateId) || cateId.isEmpty()) throw CategoryException.updateFail();
        /// verify
        Optional<Category> cateOpt = categoryService.findById(cateId);
        if(Objects.isNull(cateOpt) || cateOpt.isEmpty()) throw CategoryException.findFail();
        Category category =  cateOpt.get();
        List<ProductBase> baseList = new ArrayList<>();
        /// check product base
        if (!(Objects.isNull(baseId) || baseId.isEmpty())) {
            for (ProductBase base : baseId) {
                Optional<ProductBase> baseOpt = productBaseService.findBaseById(base.getProdBaseId());
                if (Objects.isNull(baseOpt) || baseOpt.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
                baseList.add(baseOpt.get());
            }
        }
        /// set product base
        category.getProductBase().clear();
        category.getProductBase().addAll(baseList);
        /// response
        Category resCategory = categoryService.updateCategory(category);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Category success");
        res.setRes(resCategory);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse uploadImage(String baseId, MultipartFile image) throws Exception {
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(image == null) throw FileException.fileNull();
        if(image.getSize() > 1048576 * 5 ) throw FileException.fileMaxSize();
        String contentType = image.getContentType();
        if (contentType == null) throw FileException.createFail();
        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if(!supportTypes.contains(contentType)) throw FileException.updateFailTypes();

        /// upload to sever
        //logger.info("HIT -/upload | File Name : {}", image.getOriginalFilename());
        String filePath =  fileService.upload(image);
        if(filePath == null) {
            throw ProductException.createProductFail();
        }

        /// verify
        Optional<ProductBase> prodOpt = productBaseService.findBaseById(baseId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductBase base = prodOpt.get();
        base.setImage(filePath);
        ProductBase productForm = productBaseService.updateProductBase(base);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("update Product(B) success");
        res.setRes(productForm);
        return res;
    }

    public MessageResponse deleteImage(String baseId) throws Exception {
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductBase> prodOpt = productBaseService.findBaseById(baseId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductBase base = prodOpt.get();
        base.setImage("none");
        ProductBase productForm = productBaseService.updateProductBase(base);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("update Product(B) success");
        res.setRes(productForm);
        return res;
    }
    //////////////////////////////////////////////////////////////////////////

    public MessageResponse findProductBase(String baseId, String cateId, String minPrice, String form) throws BaseException{
        /// validate
        if(!(Objects.isNull(baseId) ||  baseId.isEmpty())) {
            Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
            if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
            ProductBase base = baseOpt.get();
            ///
            if(!(Objects.isNull(form) || form.isEmpty()) && !(Objects.isNull(minPrice) || minPrice.isEmpty())){
                ///  have 2
                if (form.equals("true") && minPrice.equals("true")) {
                    Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                    ForProductBaseMinPriceFormResponse prodMinPriceAdd = productMapper.toForProductBaseMinPriceFormResponse(base, min_price);
                    /// response base min price addon By id
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(B) price addon By  ID");
                    res.setRes(prodMinPriceAdd);
                    return res;
                }
                /// if form true
                if(form.equals("true")){
                    ForProductBaseResponse baseResponse = productMapper.toForProductBaseResponse(base);
                    /// response base byId and list form
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(B) Product(f) By Base ID");
                    res.setRes(baseResponse);
                    return res;
                }
                ///  have min price
                if (minPrice.equals("true")) {
                    Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                    ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(base, min_price);
                    /// response base min price By id
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(B) price By ID");
                    res.setRes(prodMinPrice);
                    return res;
                }
            }
            /// response base byId
            MessageResponse res = new MessageResponse();
            res.setMessage("get Product(B) By ID");
            res.setRes(base);
            return res;
        }

        /// have cate id
        if(!(Objects.isNull(cateId) || cateId.isEmpty())) {
            Optional<Category> cateOpt = categoryService.findById(cateId);
            if(cateOpt.isEmpty()) throw CategoryException.findFail();
            Category cate = cateOpt.get();
            ///
            if(!(Objects.isNull(minPrice) || minPrice.isEmpty()) && !(Objects.isNull(form) || form.isEmpty())){
                ///  have 2
                if (form.equals("true") && minPrice.equals("true")) {
                    List<ForProductBaseMinPriceFormResponse> list = new ArrayList<>();
                    for (ProductBase base : cate.getProductBase()) {
                        Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                        ForProductBaseMinPriceFormResponse prodMinPriceAdd = productMapper.toForProductBaseMinPriceFormResponse(base, min_price);
                        list.add(prodMinPriceAdd);
                    }
                    /// response base min price addon By id
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(B) price addon By category ID");
                    res.setRes(list);
                    return res;
                }
                /// if form true
                if(form.equals("true")){
                    List<ForProductBaseResponse> list = productMapper.toListForProductBaseResponse(cate.getProductBase());
                    /// response base and list form by cate id
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(B) Product(f) By category ID");
                    res.setRes(list);
                    return res;
                }
                ///  have min price
                if (minPrice.equals("true")) {
                    List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                    for (ProductBase base : cate.getProductBase()) {
                        Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                        ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(base, min_price);
                        list.add(prodMinPrice);
                    }
                    /// response base min price By id
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(B) price By category ID");
                    res.setRes(list);
                    return res;
                }
            }
            /// response base By CateId
            MessageResponse res = new MessageResponse();
            res.setMessage("get Product(B) By Category ID");
            res.setRes(cate.getProductBase());
            return res;
        }
        List<ProductBase> baseList = productBaseService.findBaseAll();
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get Product(B) All");
        res.setRes(baseList);
        return res;
    }
    ///////////////////////////////////////////////

//
//
//
//    public MessageResponse findBaseAll() {
//        /// verify
//        List<ProductBase> baseList = productBaseService.findBaseAll();
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(B) All");
//        res.setRes(baseList);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse findListProductBaseByCategoryId(String cateId) throws BaseException {
//        /// validate
//        if(Objects.isNull(cateId) || cateId.isEmpty())throw CategoryException.findFail();
//        /// verify
//        Optional<Category> cateOpt = categoryService.findById(cateId);
//        if(cateOpt.isEmpty()) throw CategoryException.findFail();
//        Category category = cateOpt.get();
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(B) By Category ID");
//        res.setRes(category.getProductBase());
//        return res;
//    }
//
//    public MessageResponse findListProductBaseInfoPriceByCategoryId(String cateId) throws BaseException {
//        /// validate
//        if(Objects.isNull(cateId) || cateId.isEmpty())throw CategoryException.findFail();
//        /// verify
//        Optional<Category> cateOpt = categoryService.findById(cateId);
//        if(cateOpt.isEmpty()) throw CategoryException.findFail();
//        Category category = cateOpt.get();
//        /// check product price
//        List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
//        for (ProductBase productBase : category.getProductBase()){
//            Double minPrice = productFormService.findProductMinPriceByBaseId(productBase.getProdBaseId());
//            List<Boolean> listMateUsedEnable = materialUsedService.findEnableByBaseId(productBase.getProdBaseId());
//            ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(productBase, minPrice, listMateUsedEnable);
//            list.add(prodMinPrice);
//        }
//        /// response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(B) info price By Category ID");
//        res.setRes(list);
//        return res;
//    }
    ////////////////////////////////////////////////


    public MessageResponse deleteProductBase(String baseId) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) ||  baseId.isEmpty()) throw ProductException.findBaseFail();
        /// verify
        Boolean del = productBaseService.deleteProductBase(baseId);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Product(B) success");
        return res;
    }
    ////////////////////////////////////////////////


}
