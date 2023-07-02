package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.AddOnMapper;
import com.example.cafebackend.model.response.ForAddOnResponse;
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
    ////////////////////////////////////////

    public MessageResponse updateAddOn(String addId, String title, String isManyOptions, String isEnable, String description) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(title) || title.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(isManyOptions) || isManyOptions.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(description) || description.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> addOpt =  addOnService.findAddOnById(addId);
        if(addOpt.isEmpty()) throw OptionException.findFail();
        AddOn addOn = addOpt.get();
        /// check title
        if(!title.equals(addOn.getAddOnTitle())) {
            if(addOnService.existsByTitle(title)) throw OptionException.updateFail();
            addOn.setAddOnTitle(title);
        }
        /// check description
        if(!description.equals(addOn.getDescription())) {
            addOn.setDescription(description);
        }
        /// check isEnable
        String enable = String.valueOf(addOn.getIsEnable());
        if(!isEnable.equals(enable)){
            addOn.setIsEnable(Boolean.valueOf(isEnable));
        }
        /// check isManyOptions
        String manyOptions = String.valueOf(addOn.getIsManyOptions());
        if(!isManyOptions.equals(manyOptions)){
            addOn.setIsManyOptions(Boolean.valueOf(isManyOptions));
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

    public MessageResponse findAddOnById(String addId) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn success");
        res.setRes(add);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findAllAddOn(){
        List<AddOn> addOnList =  addOnService.findListAddOn();
        List<ForAddOnResponse> addResList = new ArrayList<>();
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn All success");
        res.setRes(addOnList);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findAddOnInfoById(String addId) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        ForAddOnResponse addRes = addOnMapper.toForAddOnResponse(add.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn Info success");
        res.setRes(addRes);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findAllAddOnInfo(){
        List<AddOn> addOnList =  addOnService.findListAddOn();

        List<ForAddOnResponse> addRes = addOnMapper.toListForAddOnResponse(addOnList);
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn Info All success");
        res.setRes(addRes);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse deleteAddOn(String addId) throws BaseException {
        Boolean addOn =  addOnService.deleteAddOn(addId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete AddOn success");
        res.setRes(addOn);
        return res;
    }
    ////////////////////////////////////////


}
