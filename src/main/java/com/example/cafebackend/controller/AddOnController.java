package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.AddOnMapper;
import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;

import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.AddOnService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.ProductForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AddOnController {

    private AddOnService addOnService;

    private ProductFormService productFormService;


    private AddOnMapper addOnMapper;


    //////////////////////////////////////////////////////////////////////
    public MessageResponse createAddOn(String title, Boolean manyOptions, String description) throws BaseException {
        /// validate
        if(Objects.isNull(title) || title.isEmpty()) throw OptionException.createFail();
        if(Objects.isNull(manyOptions)) manyOptions = false;
        if(Objects.isNull(description) || description.isEmpty()) description = EString.NONE.getValue();
        /// verify
        AddOn add = addOnService.createAddOn(title, description, manyOptions);
        MessageResponse res = new MessageResponse();
        res.setMessage("create AddOn success");
        res.setRes(add);
        return res;
    }

    public MessageResponse updateAddOn(String addId, String title, Boolean isManyOptions, Boolean isEnable, String description) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> addOpt =  addOnService.findAddOnById(addId);
        if(addOpt.isEmpty()) throw OptionException.findFail();
        AddOn addOn = addOpt.get();
        /// check title
        if (!(Objects.isNull(title) || title.isEmpty())) {
            if(!title.equals(addOn.getAddOnTitle())) {
                if(addOnService.existsByTitle(title)) throw OptionException.updateFailDuplicate();
                addOn.setAddOnTitle(title);
            }
        }
        /// check description
        if (!(Objects.isNull(description) || description.isEmpty())){
            if(!description.equals(addOn.getDescription())) {
                addOn.setDescription(description);
            }
        }
        /// check isEnable
        //String enable = String.valueOf(addOn.getIsEnable());
        if (!(Objects.isNull(isEnable) )) {
            if(!isEnable.equals(addOn.getIsEnable())){
                addOn.setIsEnable(isEnable);
            }
        }
        /// check isManyOptions
        //String manyOptions = String.valueOf(addOn.getIsManyOptions());
        if (!(Objects.isNull(isManyOptions))) {
            if(!isManyOptions.equals(addOn.getIsManyOptions())){
                addOn.setIsManyOptions(isManyOptions);
            }
        }
        /// update addon
        AddOn addRes = addOnService.updateAddOn(addOn);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("update addOn success");
        res.setRes(addRes);
        return res;
    }
    /////////////////////////////////

    public MessageResponse updateAddOnInProductForm(String prodId, List<AddOn> listAddOnId) throws Exception{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductForm> product = productFormService.findProductFormById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        ProductForm productForm = product.get();
        /// check addOn
        List<AddOn> addOnList = new ArrayList<>();
        if (!(Objects.isNull(listAddOnId)|| listAddOnId.isEmpty())) {
            for (AddOn addId : listAddOnId) {
                Optional<AddOn> addOn = addOnService.findAddOnById(addId.getAddOnId());
                if(addOn.isEmpty()) throw OptionException.findFail();
                addOnList.add(addOn.get());
            }
        }
        /// set addOn
        productForm.getAddOn().clear();
        productForm.getAddOn().addAll(addOnList);
        ProductForm prod = productFormService.updateProductForm(productForm);
        //ForProductFormAddOnResponse prodAddon = productMapper.toForProductFormAddOnResponse(prod);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("add Product(f) success");
        res.setRes(prod);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findAddOn(String addId, String formId, String option) throws BaseException {
        /// validate
        /// have add id
        if(!(Objects.isNull(addId) || addId.isEmpty())) {
            Optional<AddOn> add =  addOnService.findAddOnById(addId);
            if(add.isEmpty()) throw OptionException.findFail();
            AddOn addOn = add.get();
            ///  if option true
            if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")){
                ForAddOnResponse  addRes = addOnMapper.toForAddOnResponse(addOn);
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get Addon Option By ID");
                res.setRes(addRes);
                return res;
            }
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get AddOn By Id");
            res.setRes(add);
            return res;
        }
        /// have form id
        if (!(Objects.isNull(formId) || formId.isEmpty())) {
            Optional<ProductForm> productForm =  productFormService.findProductFormById(formId);
            if(productForm.isEmpty()) throw ProductException.findBaseFail();
            ProductForm form = productForm.get();
            ///  if option true
            if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")){
                List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(form.getAddOn());
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get list Addon Option By form ID");
                res.setRes(listAdd);
                return res;
            }
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get list Addon By form ID");
            res.setRes(form.getAddOn());
            return res;
        }
        List<AddOn> addOnList = addOnService.findListAddOn();
        ///  if option true
        if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")){
            List<ForAddOnResponse> addRes = addOnMapper.toListForAddOnResponse(addOnList);
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get Addon Option By ID");
            res.setRes(addRes);
            return res;
        }
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn All");
        res.setRes(addOnList);
        return res;

    }

//    public MessageResponse findAddOnAll(){
//        List<AddOn> addOnList =  addOnService.findListAddOn();
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get AddOn All");
//        res.setRes(addOnList);
//        return res;
//    }
//    ////////////////////////////////////////
//
//    public MessageResponse findAddOnListOptionById(String addId) throws BaseException {
//        /// validate
//        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
//        /// verify
//        Optional<AddOn> add =  addOnService.findAddOnById(addId);
//        if(add.isEmpty()) throw OptionException.findFail();
//        ForAddOnResponse addRes = addOnMapper.toForAddOnResponse(add.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Addon listOptions by ID ");
//        res.setRes(addRes);
//        return res;
//    }
    ////////////////////////////////////////

//    public MessageResponse findAddOnListOptionInfoById(String addId) throws BaseException {
//        /// validate
//        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
//        /// verify
//        Optional<AddOn> add =  addOnService.findAddOnById(addId);
//        if(add.isEmpty()) throw OptionException.findFail();
//        AddOn addOn = add.get();
//        ///
//        List<ForOptionInfoResponse> listOptInfo = new ArrayList<>();
//        for (Option option : addOn.getOptions()) {
//            List<Boolean> listMateUsedEnableOpt = materialUsedService.findEnableByOptionId(option.getOptionId());
//            ForOptionInfoResponse optInfo = addOnMapper.toForOptionInfoResponse(option, listMateUsedEnableOpt);
//            listOptInfo.add(optInfo);
//        }
//        ForAddOnOptionInfoResponse addRes = addOnMapper.toForFindMateUseInOptionResponse(addOn, listOptInfo);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Addon listOptions info by ID ");
//        res.setRes(addRes);
//        return res;
//    }
    ////////////////////////////////////////



//    public MessageResponse findAllAddOnInfo(){
//        List<AddOn> addOnList =  addOnService.findListAddOn();
//
//        List<ForAddOnResponse> addRes = addOnMapper.toListForAddOnResponse(addOnList);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get AddOn Info All success");
//        res.setRes(addRes);
//        return res;
//    }
    ////////////////////////////////////////

//    public MessageResponse findAddOnByProductFormId(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
//        if(productForm.isEmpty()) throw ProductException.findProductFail();
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get AddOn By Product(f) Id");
//        res.setRes(productForm.get().getAddOn());
//        return res;
//    }

//    public MessageResponse findAddOnListOptionByProductFormId(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
//        if(productForm.isEmpty()) throw ProductException.findProductFail();
//
//        List<ForAddOnResponse> addRes = addOnMapper.toListForAddOnResponse(productForm.get().getAddOn());
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get AddOn listOption By Product(f) Id");
//        res.setRes(addRes);
//        return res;
//    }
//
//    public MessageResponse findAddOnListOptionInfoByProductFormId(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
//        if(productForm.isEmpty()) throw ProductException.findProductFail();
//        ProductForm form = productForm.get();
//        ///
//        List<ForAddOnOptionInfoResponse> listAddOption = new ArrayList<>();
//        for (AddOn addOn : form.getAddOn()) {
//            List<ForOptionInfoResponse> listOptInfo = new ArrayList<>();
//            for (Option option : addOn.getOptions()) {
//                List<Boolean> listMateUsedEnableOpt = materialUsedService.findEnableByOptionId(option.getOptionId());
//                ForOptionInfoResponse optionInfo = addOnMapper.toForOptionInfoResponse(option, listMateUsedEnableOpt);
//                listOptInfo.add(optionInfo);
//            }
//            ForAddOnOptionInfoResponse addOption = addOnMapper.toForFindMateUseInOptionResponse(addOn, listOptInfo);
//            listAddOption.add(addOption);
//        }
//        //List<ForAddOnOptionInfoResponse> addRes = addOnMapper.toListForAddOnOptionInfoResponse(productForm.get().getAddOn());
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get AddOn listOption info By Product(f) Id");
//        res.setRes(listAddOption);
//        return res;
//    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse deleteAddOn(String addId) throws BaseException {
        Boolean addOn =  addOnService.deleteAddOn(addId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete AddOn success");
        res.setRes(addOn);
        return res;
    }
    ////////////////////////////////////////


}
