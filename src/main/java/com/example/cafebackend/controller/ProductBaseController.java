package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.FileException;
import com.example.cafebackend.exception.ProductException;

import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForFindProdcut.ForFindMateEnableAndAddOnInPFResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProdBaseAndProdFormInfoResponse;
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
        res.setMessage("create ProductBase success");
        res.setRes(productBase);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse updateProductBase(String baseId, String prodTitle, String description, String isEnable) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) ||  baseId.isEmpty()) throw ProductException.updateFailProductNull();
        if(Objects.isNull(prodTitle) ||  prodTitle.isEmpty()) throw ProductException.updateFailProductNull();
        if(Objects.isNull(description) ||  description.isEmpty()) throw ProductException.updateFailProductNull();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw ProductException.updateFailProductNull();
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if(productBase.isEmpty()) throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check prod title
        if(!prodTitle.equals(base.getProdTitle())) {
            if(productBaseService.checkExistsByTitle(prodTitle)) throw ProductException.updateFailTitleDuplicate();
            base.setProdTitle(prodTitle);
        }
        /// check description
        if(!description.equals(base.getDescription())) {
            base.setDescription(description);
        }
        /// check isEnable
        String enable = String.valueOf(base.getIsEnable());
        if(!isEnable.equals(enable)){
            base.setIsEnable(Boolean.valueOf(isEnable));
        }
        /// update product base
        ProductBase resBase =  productBaseService.updateProductBase(base);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("update ProductBase success");
        res.setRes(resBase);
        return res;
    }
    ////////////////////////////////////////////////

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
        res.setMessage("update ProductBase success");
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
        res.setMessage("update ProductBase success");
        res.setRes(productForm);
        return res;
    }
    //////////////////////////////////////////////////////////////////////////

    public MessageResponse findBaseById(String baseId) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) ||  baseId.isEmpty()) throw ProductException.findBaseFail();
        /// verify
        Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get ProductBase By ID");
        res.setRes( baseOpt.get());
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findBaseAll() {
        /// verify
        List<ProductBase> baseList = productBaseService.findBaseAll();
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get ProductBase All");
        res.setRes(baseList);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findFormInBaseId(String baseId) throws BaseException {
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
        /// verify
        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
        ProductBase productBase = baseOpt.get();
        ForProductBaseResponse baseRes = productMapper.toForProductBaseResponse(productBase);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get ProductBase info By ID");
        res.setRes(baseRes);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findFormInBaseAll() throws BaseException {
        /// verify
        List<ProductBase> baseOpt =  productBaseService.findBaseAll();
        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
        //ProductBase productBase = baseOpt.get();
        List<ForProductBaseResponse> baseRes = productMapper.toListForProductBaseResponse(baseOpt);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get ProductBase info All");
        res.setRes(baseRes);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findFormInfoInBaseId(String baseId) throws BaseException {
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
        /// verify
        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
        ProductBase productBase = baseOpt.get();
        List<ForFindMateEnableAndAddOnInPFResponse> listCheck = productMapper.toListForFindMateEnableAndAddOnInPFResponse(productBase.getProductForms());
        ForProdBaseAndProdFormInfoResponse  baseRes = productMapper.toForProdBaseAndProdFormInfoResponse(productBase, listCheck);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get info ProductBase info By ID");
        res.setRes(baseRes);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse deleteProductBase(String baseId) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) ||  baseId.isEmpty()) throw ProductException.findBaseFail();
        /// verify
        Boolean del = productBaseService.deleteProductBase(baseId);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("delete ProductBase success");
        return res;
    }
    ////////////////////////////////////////////////


}
