package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;

import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class ProductBaseController {

    private ProductBaseService productBaseService;

    ////////////////////////////////////////////////

    public MessageResponse createProductBase(String prodTitle) throws BaseException{
        /// validate
        if(Objects.isNull(prodTitle) ||  prodTitle.isEmpty()) throw ProductException.createBaseFail();
        /// verify
        ProductBase productBase = productBaseService.createProductBase(prodTitle);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("create product base complete");
        res.setRes(productBase);
        return res;
    }

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
        if(isEnable.equals("true")){
            if(base.getIsEnable().equals(false)) base.setIsEnable(true);
        } else if (isEnable.equals("false")) {
            if(base.getIsEnable().equals(true)) base.setIsEnable(false);
        }
        /// update product base
        ProductBase resBase =  productBaseService.updateBaseProduct(base);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("update product base complete");
        res.setRes(resBase);
        return res;
    }


    public MessageResponse deleteProductBase(String baseId) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) ||  baseId.isEmpty()) throw ProductException.findBaseFail();
        /// verify
        Boolean del = productBaseService.deleteProductBase(baseId);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("delete product base complete");
        return res;
    }



}
