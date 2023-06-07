package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.CategoryMapper;
import com.example.cafebackend.mapper.ProductMapper;

import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class BaseProductController {

    private BaseProductService baseProductService;

    ////////////////////////////////////////////////

    public MessageResponse createBaseProduct(String prodTitle, String description) throws BaseException{
        /// validate
        if(Objects.isNull(prodTitle) ||  prodTitle.isEmpty()) throw ProductException.createBaseFail();
        if(Objects.isNull(description) ||  description.isEmpty()) description = "none";
        /// verify
        BaseProduct baseProduct = baseProductService.createBaseProduct(prodTitle, description);
        MessageResponse res = new MessageResponse();
        res.setMessage("create product complete");
        res.setRes(baseProduct);
        return res;
    }

}
