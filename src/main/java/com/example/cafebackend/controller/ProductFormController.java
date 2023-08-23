package com.example.cafebackend.controller;

import com.example.cafebackend.exception.*;
import com.example.cafebackend.mapper.AddOnMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.*;
import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;
import com.example.cafebackend.model.response.ForFindProdcut.*;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;

@AllArgsConstructor
@Service
public class ProductFormController {

    private ProductFormService productFormService;

    private ProductBaseService productBaseService;

    private ProductMapper productMapper;

    private AddOnMapper addOnMapper;



    //////////////////////////////////////////////////////////////////////////

    public MessageResponse createProductForm(String baseId, String prodForm, Double prodPrice, String description) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw ProductException.createFailRequestBaseNull();
        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.createFailRequestFormNull();
        if(Objects.isNull(prodPrice)) throw ProductException.createFailPriceRequestNull();
        if(Objects.isNull(description) || description.isEmpty()) description = "none";
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if(productBase.isEmpty()) throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check product form
        if(productFormService.checkExistsByForm(prodForm)) {
            for(String listForm :  productFormService.findFormByBaseId(base.getProdBaseId()))
                if (listForm.equals(prodForm)) throw ProductException.createFailFormDuplicate();
        }
        /// create product form
        //Double setPrice = Double.valueOf(prodPrice);
        ProductForm productForm = productFormService.createProductForm(base, prodForm, prodPrice, description);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("add Product(f) success");
        res.setRes(productForm);
        return res;
    }

    public MessageResponse updateProductForm(String formId, String prodForm, Double prodPrice, String description, Boolean isEnable) throws Exception{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductForm form = prodOpt.get();
        /// check product form
        if(!(Objects.isNull(prodForm) || prodForm.isEmpty())) {
            if(!prodForm.equals(form.getProdForm())) {
                for(String listForm : productFormService.findFormByBaseId(form.getProductBase().getProdBaseId())){
                    if (listForm.equals(prodForm)) throw ProductException.updateFailFormDuplicate();
                }
                form.setProdForm(prodForm);
            }
        }
        /// check price
//        Double setPrice = Double.valueOf(prodPrice);
        if (!(Objects.isNull(prodPrice))) {
            if(!prodPrice.equals(form.getPrice())) {
                form.setPrice(prodPrice);
            }
        }

        /// check description
        if (!(Objects.isNull(description) || description.isEmpty())) {
            if(!description.equals(form.getDescription())) {
                form.setDescription(description);
            }
        }
        /// check isEnable
//        String enable = String.valueOf(form.getIsEnable());
        if (!(Objects.isNull(isEnable))) {
            if(!isEnable.equals(form.getIsEnable())){
                form.setIsEnable(isEnable);
            }
        }


        /// update product form
        ProductForm prod = productFormService.updateProductForm(form);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Product(f) success");
        res.setRes(prod);
        return res;
    }
    //////////////////////////////////////////////////////////////////////////

//    public MessageResponse findProductFormById(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
//        if(productForm.isEmpty()) throw ProductException.findProductFail();
//        ForProductFormResponse response = productMapper.toForProductFormResponse(productForm.get());
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(f) By ID");
//        res.setRes(response);
//        return res;
//    }
//
//    public MessageResponse findProductFormAll() {
//        /// verify
//        List<ProductForm> productForm =  productFormService.findListProduct();
//        List<ForProductFormResponse> response = productMapper.toListForProductFormResponse(productForm);
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(f) All");
//        res.setRes(response);
//        return res;
//    }
    ////////////////////////////////////////////////////////////////

//    public MessageResponse findProductFormInFoById(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
//        if(productForm.isEmpty()) throw ProductException.findProductFail();
//        /// get res
//
//        List<Boolean> listMateUsedEnable = materialUsedService.findEnableByFormId(productForm.get().getProdFormId());
//        ForProductFormInfoResponse prodRes = productMapper.toForProductFormInfoResponse(productForm.get(), listMateUsedEnable);
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(f) info By ID");
//        res.setRes(prodRes);
//        return res;
//    }

//    public MessageResponse findProductFormInFoAddonOptionInfoById(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
//        if(productForm.isEmpty()) throw ProductException.findProductFail();
//        ProductForm form = productForm.get();
//        /// get res
//        List<ForAddOnOptionInfoResponse> listAddOption = new ArrayList<>();
//        for (AddOn addOn : form.getAddOn()) {
//            List<ForOptionInfoResponse> listOptInfo = new ArrayList<>();
//            for (Option option : addOn.getOptions()) {
//                List<Boolean> listMateUsedEnable2 = materialUsedService.findEnableByOptionId(option.getOptionId());
//                ForOptionInfoResponse optionInfo = addOnMapper.toForOptionInfoResponse(option, listMateUsedEnable2);
//                listOptInfo.add(optionInfo);
//            }
//            ForAddOnOptionInfoResponse addOption = addOnMapper.toForFindMateUseInOptionResponse(addOn, listOptInfo);
//            listAddOption.add(addOption);
//        }
//        //List<ForAddOnResponse> addRes = addOnMapper.toListForAddOnResponse(form.getAddOn());
//        List<Boolean> listMateUsedEnable = materialUsedService.findEnableByFormId(productForm.get().getProdFormId());
//        ForProdFormInfoAddOnOptionInfoResponse prodRes = productMapper.toForProdFormInfoAddOnOptionInfoResponse(form, listMateUsedEnable, listAddOption);
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Product(f) info Addon Option info By ID");
//        res.setRes(prodRes);
//        return res;
//    }
    ////////////////////////////////////////////////////////////////


    public MessageResponse findProductForm(String formId, String baseId, String addOn, String option) throws BaseException {
        /// verify
        ///////////////////////////
        /// if form value
        if (!(Objects.isNull(formId) || formId.isEmpty())) {
            Optional<ProductForm> productForm =  productFormService.findProductFormById(formId);
            if(productForm.isEmpty()) throw ProductException.findProductFail();
            ProductForm form = productForm.get();
            /// if addon true
            if (!(Objects.isNull(addOn) || addOn.isEmpty()) && addOn.equals("true")) {
                ///  if option true
                if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")){
                        List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(form.getAddOn());
                        ForProductFormInfoAddOnOptionResponse pf = productMapper.toForProductFormInfoAddOnOptionResponse(form, listAdd);
                    /// res
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get Product(f) Addon Option By Base ID");
                    res.setRes(pf);
                    return res;
                }
                ForProductFormInfoAddOnResponse pfAddon = productMapper.toForProductFormInfoAddOnResponse(form);
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(f) Addon By Base ID");
                res.setRes(pfAddon);
                return res;
            }
            ForProductFormInfoResponse response = productMapper.toForProductFormInfoResponse(productForm.get());
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get Product(f) By ID");
            res.setRes(response);
            return res;
        }
        /// if base value
        if(!(Objects.isNull(baseId) || baseId.isEmpty())) {
            Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
            if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
            ProductBase productBase = baseOpt.get();
            /// if addon true
            if (!(Objects.isNull(addOn) || addOn.isEmpty()) && addOn.equals("true")) {
                ///  if option true
                if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")){
                    List<ForProductFormAddOnOptionResponse> responses = new ArrayList<>();
                    for (ProductForm form : productBase.getProductForms()){
                        List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(form.getAddOn());
                        ForProductFormAddOnOptionResponse pf = productMapper.toForProductFormAddOnOptionResponse(form, listAdd);
                        responses.add(pf);
                    }
                    /// res
                    MessageResponse res = new MessageResponse();
                    res.setMessage("get list Product(f) Addon Option By Base ID");
                    res.setRes(responses);
                    return res;
                }
                List<ForProductFormAddOnResponse> pfAddon = productMapper.toListForProductFormAddOnResponse(productBase.getProductForms());
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get list Product(f) Addon By Base ID");
                res.setRes(pfAddon);
                return res;
            }
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get list Product(f) By Base ID");
            res.setRes(productBase.getProductForms());
            return res;
        }
        /// res if all not value
        List<ProductForm> productForm =  productFormService.findListProduct();
        List<ForProductFormInfoResponse> response = productMapper.toListForProductFormInfoResponse(productForm);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get list Product(f) All");
        res.setRes(response);
        return res;
    }

//    public MessageResponse findProductFormAddOnByBaseId(String baseId) throws BaseException {
//        /// validate
//        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
//        /// verify
//        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
//        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
//        ProductBase productBase = baseOpt.get();
//        ///
//        List<ForProductFormInfoAddOnResponse> list = new ArrayList<>();
//        for (ProductForm productForm : productBase.getProductForms()){
//            ForProductFormInfoAddOnResponse pfInfoAddon = productMapper.toForProductFormInfoAddOnResponse(productForm);
//            list.add(pfInfoAddon);
//        }
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Product(f) info Addon By Base ID");
//        res.setRes(list);
//        return res;
//    }
//
//    public MessageResponse findProductFormAddOnOptionByBaseId(String baseId) throws BaseException {
//        /// validate
//        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
//        /// verify
//        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
//        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
//        ProductBase productBase = baseOpt.get();
//        ///
//        List<ForProductFormAddOnOptionResponse> responses = new ArrayList<>();
//        for (ProductForm productForm : productBase.getProductForms()){
//            List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(productForm.getAddOn());
//            ForProductFormAddOnOptionResponse pf = productMapper.toForProductFormAddOnOptionResponse(productForm, listAdd);
//            responses.add(pf);
//        }
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Product(f) Addon Option By Base ID");
//        res.setRes(responses);
//        return res;
//    }


//    /////////////////  f
//    public MessageResponse findProductFormInfoByBaseId(String baseId) throws BaseException {
//        /// validate
//        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
//        /// verify
//        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
//        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
//        ProductBase productBase = baseOpt.get();
//        ///
//        List<ForProductFormInfoResponse> list = new ArrayList<>();
//        for (ProductForm productForm : productBase.getProductForms()){
//            List<Boolean> listMateUsedEnable = materialUsedService.findEnableByFormId(productForm.getProdFormId());
//            ForProductFormInfoResponse pfInfo = productMapper.toForProductFormInfoResponse(productForm, listMateUsedEnable);
//            list.add(pfInfo);
//        }
//        //List<ForProductFormInfoResponse> listCheck = productMapper.toListForProductFormInfoResponse(productBase.getProductForms());
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Product(f) info By Base ID");
//        res.setRes(list);
//        return res;
//    }
//
//    public MessageResponse findProductFormInfoAddOnByBaseId(String baseId) throws BaseException {
//        /// validate
//        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
//        /// verify
//        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
//        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
//        ProductBase productBase = baseOpt.get();
//        ///
//        List<ForProductFormInfoAddOnResponse> list = new ArrayList<>();
//        for (ProductForm productForm : productBase.getProductForms()){
//            ForProductFormInfoAddOnResponse pfInfoAddon = productMapper.toForProductFormInfoAddOnResponse(productForm);
//            list.add(pfInfoAddon);
//        }
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Product(f) info Addon By Base ID");
//        res.setRes(list);
//        return res;
//    }
//
//    public MessageResponse findProductFormInfoAddOnOptionInfoByBaseId(String baseId) throws BaseException {
//        /// validate
//        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
//        /// verify
//        Optional<ProductBase> baseOpt =  productBaseService.findBaseById(baseId);
//        if(baseOpt.isEmpty()) throw ProductException.findBaseFail();
//        ProductBase productBase = baseOpt.get();
//        ///
//        List<ForProdFormInfoAddOnOptionInfoResponse> list = new ArrayList<>();
//        for (ProductForm productForm : productBase.getProductForms()){
//            List<ForAddOnOptionInfoResponse> listAddOption = new ArrayList<>();
//            for (AddOn addOn : productForm.getAddOn()) {
//                List<ForOptionInfoResponse> listOptInfo = new ArrayList<>();
//                for (Option option : addOn.getOptions()) {
//                    List<Boolean> listMateUsedEnableOpt = materialUsedService.findEnableByOptionId(option.getOptionId());
//                    ForOptionInfoResponse optionInfo = addOnMapper.toForOptionInfoResponse(option, listMateUsedEnableOpt);
//                    listOptInfo.add(optionInfo);
//                }
//                ForAddOnOptionInfoResponse addOption = addOnMapper.toForFindMateUseInOptionResponse(addOn, listOptInfo);
//                listAddOption.add(addOption);
//            }
//            List<Boolean> listMateUsedEnable = materialUsedService.findEnableByFormId(productForm.getProdFormId());
//            ForProdFormInfoAddOnOptionInfoResponse pfInfoAddon = productMapper.toForProdFormInfoAddOnOptionInfoResponse(productForm, listMateUsedEnable ,listAddOption);
//            list.add(pfInfoAddon);
//        }
//        //List<ForProdFormInfoAddOnOptionInfoResponse> prodRes = productMapper.toListForProdFormInfoAddOnOptionInfoResponse(productBase.getProductForms());
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Product(f) info Addon Option info By Base ID");
//        res.setRes(list);
//        return res;
//    }
    ////////////////////////////////////////////////



    public MessageResponse deleteProductForm(String prodId) throws BaseException {
        Boolean product =  productFormService.deleteFormById(prodId);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Product(f) success");
        res.setRes(product);
        return res;
    }
    ////////////////////////////////////////////////////////////////

}
