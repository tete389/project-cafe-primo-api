package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.CategoryException;
import com.example.cafebackend.exception.FileException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.MaterialMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseCateMateUseResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseCategoryResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseFormCountResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseFormNameCategoryResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseFormNameResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceFormNameResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceFormResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private MaterialMapper materialMapper;

    private FileService fileService;

    ////////////////////////////////////////////////

    public MessageResponse createProductBase(String prodTitleTh, String prodTitleEng) throws BaseException {
        /// validate
        if (Objects.isNull(prodTitleTh) || prodTitleTh.isEmpty())
            throw ProductException.createBaseFail();
        if (Objects.isNull(prodTitleEng) || prodTitleEng.isEmpty())
            throw ProductException.createBaseFail();
        /// verify
        if (productBaseService.checkExistsByTitleTh(prodTitleTh)) {
            if (!productBaseService.checkDeleteByTitleTh(prodTitleTh))
                throw ProductException.createFailTitleDuplicate();
        }
        if (productBaseService.checkExistsByTitleEng(prodTitleEng)) {
            if (!productBaseService.checkDeleteByTitleEng(prodTitleEng))
                throw ProductException.createFailTitleDuplicate();
        }

        ProductBase productBase = productBaseService.createProductBase(prodTitleTh, prodTitleEng);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("create Product(B) success");
        res.setRes(productBase);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateProductBase(String baseId, String prodTitleTh, String prodTitleEng, String description,
            Boolean isEnable)
            throws BaseException {
        /// validate
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw ProductException.updateFailProductNull();
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if (productBase.isEmpty())
            throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check prod title
        if (!(Objects.isNull(prodTitleTh) || prodTitleTh.isEmpty())) {
            if (!prodTitleTh.equals(base.getProdTitleTh())) {
                Boolean check = productBaseService.checkExistsByTitleTh(prodTitleTh);
                if (check) {
                    if (!productBaseService.checkDeleteByTitleTh(prodTitleTh)) {
                        throw ProductException.updateFailTitleDuplicate();
                    }
                }
                base.setProdTitleTh(prodTitleTh);
            }
        }
        if (!(Objects.isNull(prodTitleEng) || prodTitleEng.isEmpty())) {
            if (!prodTitleEng.equals(base.getProdTitleEng())) {
                if (productBaseService.checkExistsByTitleEng(prodTitleEng)) {
                    if (!productBaseService.checkDeleteByTitleEng(prodTitleEng)) {
                        throw ProductException.updateFailTitleDuplicate();
                    }

                }
                base.setProdTitleEng(prodTitleEng);
            }
        }
        /// check description
        if (!(Objects.isNull(description) || description.isEmpty())) {
            if (!description.equals(base.getDescription())) {
                base.setDescription(description);
            }
        }
        /// check isEnable
        // String enable = String.valueOf(base.getIsEnable());
        if (!(Objects.isNull(isEnable))) {
            if (!isEnable.equals(base.getIsEnable())) {
                base.setIsEnable(isEnable);
            }
        }
        /// update product base
        ProductBase resBase = productBaseService.updateProductBase(base);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Product(B) success");
        res.setRes(resBase);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateProductBaseIntoCategory(String cateId, List<ProductBase> baseId) throws BaseException {
        /// validate
        if (Objects.isNull(cateId) || cateId.isEmpty())
            throw CategoryException.updateFail();
        /// verify
        Optional<Category> cateOpt = categoryService.findById(cateId);
        if (Objects.isNull(cateOpt) || cateOpt.isEmpty())
            throw CategoryException.findFail();
        Category category = cateOpt.get();

        /// check product base
        if (!(Objects.isNull(baseId) || baseId.isEmpty())) {
            for (ProductBase base : baseId) {
                Optional<ProductBase> baseOpt = productBaseService.findBaseById(base.getProdBaseId());
                if (Objects.isNull(baseOpt) || baseOpt.isEmpty()) {
                    throw CategoryException.addInfoFailCategoryNull();
                }
                baseOpt.get().getCategory().add(category);
                productBaseService.updateProductBase(baseOpt.get());
            }
        }
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Category success");
        res.setRes(category);
        return res;
    }

    public MessageResponse deleteProductBaseFromCategory(String cateId, List<ProductBase> baseId) throws BaseException {
        /// validate
        if (Objects.isNull(cateId) || cateId.isEmpty())
            throw CategoryException.updateFail();
        /// verify
        Optional<Category> cateOpt = categoryService.findById(cateId);
        if (Objects.isNull(cateOpt) || cateOpt.isEmpty())
            throw CategoryException.findFail();
        Category category = cateOpt.get();

        /// check product base
        if (!(Objects.isNull(baseId) || baseId.isEmpty())) {
            for (ProductBase base : baseId) {
                Optional<ProductBase> baseOpt = productBaseService.findBaseById(base.getProdBaseId());
                if (Objects.isNull(baseOpt) || baseOpt.isEmpty()) {
                    throw CategoryException.addInfoFailCategoryNull();
                }
                baseOpt.get().getCategory().removeIf(item -> item.getCateId().equals(category.getCateId()));
                productBaseService.updateProductBase(baseOpt.get());
            }
        }
        /// set product base
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Category success");
        res.setRes(category);
        return res;
    }

    ////////////////////////////////////////

    public MessageResponse updateCategoryIntoProductBase(String baseId, List<Category> cateId) throws BaseException {
        /// validate
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw CategoryException.updateFail();
        /// verify
        Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
        if (Objects.isNull(baseOpt) || baseOpt.isEmpty())
            throw CategoryException.findFail();
        ProductBase prodBase = baseOpt.get();
        /// check product base
        // List<Category> cateList = new ArrayList<>();
        if (!(Objects.isNull(cateId) || cateId.isEmpty())) {
            for (Category cate : cateId) {
                Optional<Category> cateOpt = categoryService.findById(cate.getCateId());
                if (Objects.isNull(cateOpt) || cateOpt.isEmpty()) {
                    throw CategoryException.addInfoFailCategoryNull();
                }
                prodBase.getCategory().add(cateOpt.get());
            }
        }
        /// set product base
        // prodBase.getCategory().addAll(cateList);
        /// response
        ProductBase resProductBase = productBaseService.updateProductBase(prodBase);
        MessageResponse res = new MessageResponse();
        res.setMessage("update success");
        res.setRes(resProductBase);
        return res;
    }

    public MessageResponse deleteCategoryFromProductBase(String baseId, List<Category> cateId) throws BaseException {
        /// validate
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw CategoryException.updateFail();
        /// verify
        Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
        if (Objects.isNull(baseOpt) || baseOpt.isEmpty())
            throw CategoryException.findFail();
        ProductBase prodBase = baseOpt.get();
        /// check product base
        if (!(Objects.isNull(cateId) || cateId.isEmpty())) {
            for (Category cate : cateId) {
                prodBase.getCategory().removeIf(item -> item.getCateId().equals(cate.getCateId()));
            }
        }
        /// response
        ProductBase resProductBase = productBaseService.updateProductBase(prodBase);
        MessageResponse res = new MessageResponse();
        res.setMessage("update success");
        res.setRes(resProductBase);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse uploadImage(String baseId, MultipartFile image) throws Exception {
        /// validate
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw ProductException.findFailRequestProductIdNull();
        if (image == null)
            throw FileException.fileNull();
        if (image.getSize() > 1048576 * 5)
            throw FileException.fileMaxSize();
        String contentType = image.getContentType();
        if (contentType == null)
            throw FileException.createFail();
        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportTypes.contains(contentType))
            throw FileException.updateFailTypes();

        /// upload to sever
        // logger.info("HIT -/upload | File Name : {}", image.getOriginalFilename());
        String filePath = fileService.upload(image);
        if (filePath == null) {
            throw ProductException.createProductFail();
        }

        /// verify
        Optional<ProductBase> prodOpt = productBaseService.findBaseById(baseId);
        if (prodOpt.isEmpty())
            throw ProductException.findProductFail();
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
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductBase> prodOpt = productBaseService.findBaseById(baseId);
        if (prodOpt.isEmpty())
            throw ProductException.findProductFail();
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

    public MessageResponse findProductBase(String baseId, String cateId, String minPrice, String haveForm,
            String haveCountFrom, String haveMateUse, String haveCate, Integer pageSize, Integer pageNum)
            throws BaseException {

        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 30;
        }
        /// have base id
        if (!(Objects.isNull(baseId) || baseId.isEmpty())) {
            MessageResponse resByid = findProductBaseById(baseId, haveForm, haveCate, haveMateUse, minPrice);
            return resByid;
        }

        /// have cate id
        if (!(Objects.isNull(cateId) || cateId.isEmpty())) {
            MessageResponse resByCateId = findProductBaseByCateId(cateId, haveForm, minPrice);
            return resByCateId;
        }

        /// no baseid no cateid = find all
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<ProductBase> baseList = new ArrayList<>();
        if (!(Objects.isNull(haveCountFrom) || haveCountFrom.isEmpty())) {

            List<ForProductBaseFormCountResponse> baseCfObject = productBaseService
                    .findBaseAllAndCountFromASCPageable(pageable);
            List<ForProductBaseFormCountResponse> baseCfObject2 = productBaseService
                    .findBaseAllAndNoFromASCPageable(pageable);

            Set<ForProductBaseFormCountResponse> baseCfObject3 = new HashSet<>();

            baseCfObject3.addAll(baseCfObject);
            baseCfObject3.addAll(baseCfObject2);
            MessageResponse res = new MessageResponse();
            res.setMessage("get Product(B) Product(f Count)");
            res.setRes(baseCfObject);
            return res;
        } else {
            baseList = productBaseService.findBaseAllASCPageable(pageable);
        }

        if (!(Objects.isNull(haveForm) || haveForm.isEmpty())) {
            /// if form true
            if (haveForm.equals("true")) {
                List<ForProductBaseResponse> list = productMapper
                        .toListForProductBaseResponse(baseList);
                /// response base and list form by cate id
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) Product(f) ");
                res.setRes(list);
                return res;
            }
            if (haveForm.equals(EString.Name.getValue())) {
                List<ForProductBaseFormNameResponse> listBaseFormName = new ArrayList<>();
                for (ProductBase base : baseList) {
                    List<String> listFormNmae = productFormService.findFormThByBaseId(base.getProdBaseId());
                    ForProductBaseFormNameResponse baseForm = productMapper.toForProductBaseFormNameResponse(base,
                            listFormNmae);
                    listBaseFormName.add(baseForm);
                }
                /// response base byId and list form name
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) Product(f name)");
                res.setRes(listBaseFormName);
                return res;
            }
        }
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get Product(B) All");
        res.setRes(baseList);
        return res;
    }

    ////////////////////////////////////////////////

    public MessageResponse deleteProductBase(String baseId) throws BaseException {
        /// validate
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw ProductException.findBaseFail();
        /// verify
        Boolean product = productBaseService.deleteProductBase(baseId);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Product(B) success");
        res.setRes(product);
        return res;
    }
    ////////////////////////////////////////////////
    ///////////////////////////////////////////////

    private MessageResponse findProductBaseById(String baseId, String haveForm, String haveCate, String haveMateUse,
            String minPrice) throws BaseException {

        Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
        if (baseOpt.isEmpty())
            throw ProductException.findBaseFail();
        ProductBase base = baseOpt.get();
        ///
        if (!(Objects.isNull(haveForm) || haveForm.isEmpty())
                && !(Objects.isNull(minPrice) || minPrice.isEmpty())) {
            /// have 2
            if (minPrice.equals("true") && haveForm.equals("true")) {
                Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                ForProductBaseMinPriceFormResponse prodMinPriceAdd = productMapper
                        .toForProductBaseMinPriceFormResponse(base, min_price);
                /// response base min price addon By id
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) price addon By  ID");
                res.setRes(prodMinPriceAdd);
                return res;
            }

            /// have 2
            if (minPrice.equals("true") && haveForm.equals(EString.Name.getValue())) {
                Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                List<String> listFormNmae = productFormService.findFormThByBaseId(base.getProdBaseId());
                ForProductBaseMinPriceFormNameResponse prodMinPriceAdd = productMapper
                        .toForProductBaseMinPriceFormNameResponse(base, min_price, listFormNmae);
                /// response base min price addon By id
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) price addon By  ID");
                res.setRes(prodMinPriceAdd);
                return res;
            }
        }

        if (!(Objects.isNull(haveCate) || haveCate.isEmpty())) {
            if (haveCate.equals("true")) {
                if (!(Objects.isNull(haveForm) || haveForm.isEmpty())) {
                    /// if form true
                    if (haveForm.equals("true")) {
                        ForProductBaseCategoryResponse baseResponse = productMapper
                                .toForProductBaseCategoryResponse(base);
                        /// response base byId and list form
                        MessageResponse res = new MessageResponse();
                        res.setMessage("get Product(B) Product(f) listCate By Base ID");
                        res.setRes(baseResponse);
                        return res;
                    }

                    if (haveForm.equals(EString.Name.getValue())) {
                        List<String> listFormNmae = productFormService.findFormThByBaseId(base.getProdBaseId());
                        ForProductBaseFormNameCategoryResponse baseForm = productMapper
                                .toForProductBaseFormNameCategoryResponse(base,
                                        listFormNmae);
                        /// response base byId and list form name
                        MessageResponse res = new MessageResponse();
                        res.setMessage("get Product(B) Product(fname) listCate By Base ID");
                        res.setRes(baseForm);
                        return res;
                    }
                }
                if (!(Objects.isNull(haveMateUse) || haveMateUse.isEmpty())) {
                    if (haveMateUse.equals("true")) {
                        List<MaterialUsedNec> ListMateNec = new ArrayList<>();
                        for (MaterialUsed mateUsed : base.getMaterialUsed()) {
                            MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed,
                                    mateUsed.getMaterial());
                            ListMateNec.add(materialUsedNec);
                        }
                        ForProductBaseCateMateUseResponse baseCateMate = new ForProductBaseCateMateUseResponse();
                        baseCateMate.setMaterialUse(ListMateNec);
                        baseCateMate.setCategory(base.getCategory());
                        baseCateMate.setProdBaseId(base.getProdBaseId());
                        baseCateMate.setProdTitleTh(base.getProdTitleTh());
                        baseCateMate.setProdTitleEng(base.getProdTitleEng());
                        baseCateMate.setIsEnable(base.getIsEnable());
                        baseCateMate.setDescription(base.getDescription());
                        baseCateMate.setImage(base.getImage());
                        baseCateMate.setIsMaterialEnable(base.getIsMaterialEnable());
                        /// response
                        MessageResponse res = new MessageResponse();
                        res.setMessage("get  Product(B)  mateUsed cate By ID ");
                        res.setRes(baseCateMate);
                        return res;
                    }
                }
                ForProductBaseCategoryResponse baseCate = new ForProductBaseCategoryResponse();
                baseCate.setCategory(base.getCategory());
                baseCate.setProdBaseId(base.getProdBaseId());
                baseCate.setProdTitleTh(base.getProdTitleTh());
                baseCate.setProdTitleEng(base.getProdTitleEng());
                baseCate.setIsEnable(base.getIsEnable());
                baseCate.setDescription(base.getDescription());
                baseCate.setImage(base.getImage());
                baseCate.setIsMaterialEnable(base.getIsMaterialEnable());
                /// response
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) cate By ID");
                res.setRes(baseCate);
                return res;
            }

        }

        if (!(Objects.isNull(haveMateUse) || haveMateUse.isEmpty())) {
            if (haveMateUse.equals("true")) {
                List<MaterialUsedNec> ListMateNec = new ArrayList<>();
                for (MaterialUsed mateUsed : base.getMaterialUsed()) {
                    MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed,
                            mateUsed.getMaterial());
                    ListMateNec.add(materialUsedNec);
                }

                ForProductBaseCateMateUseResponse baseCateMate = new ForProductBaseCateMateUseResponse();
                baseCateMate.setMaterialUse(ListMateNec);
                baseCateMate.setProdBaseId(base.getProdBaseId());
                baseCateMate.setProdTitleTh(base.getProdTitleTh());
                baseCateMate.setProdTitleEng(base.getProdTitleEng());
                baseCateMate.setIsEnable(base.getIsEnable());
                baseCateMate.setDescription(base.getDescription());
                baseCateMate.setImage(base.getImage());
                baseCateMate.setIsMaterialEnable(base.getIsMaterialEnable());

                /// response
                MessageResponse res = new MessageResponse();
                res.setMessage("get  Product(B)  materialUsed By ID ");
                res.setRes(baseCateMate);
                return res;
            }

        }

        if (!(Objects.isNull(haveForm) || haveForm.isEmpty())) {
            /// if form true
            if (haveForm.equals("true")) {
                ForProductBaseResponse baseResponse = productMapper.toForProductBaseResponse(base);
                /// response base byId and list form
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) Product(f) By Base ID");
                res.setRes(baseResponse);
                return res;
            }

            if (haveForm.equals(EString.Name.getValue())) {
                List<String> listFormNmae = productFormService.findFormThByBaseId(base.getProdBaseId());
                ForProductBaseFormNameResponse baseForm = productMapper.toForProductBaseFormNameResponse(base,
                        listFormNmae);
                /// response base byId and list form name
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) Product(f name) By Base ID");
                res.setRes(baseForm);
                return res;
            }
        }

        if (!(Objects.isNull(minPrice) || minPrice.isEmpty())) {
            /// have min price
            if (minPrice.equals("true")) {
                Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                ForProductBaseMinPriceResponse prodMinPrice = productMapper.toForProductBaseMinPriceResponse(base,
                        min_price);
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

    private MessageResponse findProductBaseByCateId(String cateId, String haveForm, String minPrice)
            throws CategoryException {
        Optional<Category> cateOpt = categoryService.findById(cateId);
        if (cateOpt.isEmpty())
            throw CategoryException.findFail();
        Category cate = cateOpt.get();
        ///
        if (!(Objects.isNull(minPrice) || minPrice.isEmpty())
                && !(Objects.isNull(haveForm) || haveForm.isEmpty())) {
            /// have 2
            if (haveForm.equals("true") && minPrice.equals("true")) {
                List<ForProductBaseMinPriceFormResponse> list = new ArrayList<>();
                for (ProductBase base : cate.getProductBase()) {
                    Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                    ForProductBaseMinPriceFormResponse prodMinPriceAdd = productMapper
                            .toForProductBaseMinPriceFormResponse(base, min_price);
                    list.add(prodMinPriceAdd);
                }
                /// response base min price addon By id
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) price Product(f)   By category ID");
                res.setRes(list);
                return res;
            }
        }
        if (!(Objects.isNull(haveForm) || haveForm.isEmpty())) {
            /// if form true
            if (haveForm.equals("true")) {
                List<ForProductBaseResponse> list = productMapper
                        .toListForProductBaseResponse(cate.getProductBase());
                /// response base and list form by cate id
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) Product(f) By category ID");
                res.setRes(list);
                return res;
            }
            if (haveForm.equals(EString.Name.getValue())) {
                List<ForProductBaseFormNameResponse> listBaseFormName = new ArrayList<>();
                for (ProductBase base : cate.getProductBase()) {
                    List<String> listFormNmae = productFormService.findFormThByBaseId(base.getProdBaseId());
                    ForProductBaseFormNameResponse baseForm = productMapper.toForProductBaseFormNameResponse(base,
                            listFormNmae);
                    listBaseFormName.add(baseForm);
                }
                /// response base byId and list form name
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(B) Product(f name) By category ID");
                res.setRes(listBaseFormName);
                return res;
            }
        }
        if (!(Objects.isNull(minPrice) || minPrice.isEmpty())) {
            /// have min price
            if (minPrice.equals("true")) {
                List<ForProductBaseMinPriceResponse> list = new ArrayList<>();
                for (ProductBase base : cate.getProductBase()) {
                    Double min_price = productFormService.findProductMinPriceByBaseId(base.getProdBaseId());
                    ForProductBaseMinPriceResponse prodMinPrice = productMapper
                            .toForProductBaseMinPriceResponse(base, min_price);
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

}
