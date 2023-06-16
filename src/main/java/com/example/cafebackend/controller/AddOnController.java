package com.example.cafebackend.controller;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.BaseException;
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
        if(Objects.isNull(description) || description.isEmpty()) description = "none";
        /// verify
        AddOn add = addOnService.createAddOn(title, description, manyOptions);
        MessageResponse res = new MessageResponse();
        res.setMessage("create AddOn complete");
        res.setRes(add);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse findAddOnById(String addId) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn complete");
        res.setRes(add);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse findAllAddOn(){
        List<AddOn> addOnList =  addOnService.findListAddOn();
        List<ForAddOnResponse> addResList = new ArrayList<>();
        for (AddOn addOn : addOnList){
            List<ProductForm> prodList = productFormService.findProductByAddOnlId(addOn.getAddOnId());
            ForAddOnResponse addRes = addOnMapper.toForAddOnResponse(addOn, prodList);
            addResList.add(addRes);
        }
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn complete");
        res.setRes(addResList);
        return res;
    }


    ////////////////////////////////////////

    public MessageResponse setAddOnTitle(String addId, String newTitle) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(newTitle) || newTitle.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        if (addOnService.existsByTitle(newTitle)) throw OptionException.updateFail();
        add.get().setAddOnTitle(newTitle);
        AddOn addRes = addOnService.updateAddOn(add.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update title complete");
        res.setRes(addRes);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse setAddOnManyOptions(String addId, Boolean isOptions) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(isOptions)) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        add.get().setIsManyOptions(isOptions);
        AddOn addRes = addOnService.updateAddOn(add.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update IsManyOptions complete");
        res.setRes(addRes);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse setAddOnEnable(String addId, Boolean isEnable) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(isEnable)) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        add.get().setIsEnable(isEnable);
        AddOn addRes = addOnService.updateAddOn(add.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update IsEnable complete");
        res.setRes(addRes);
        return res;
    }
    ////////////////////////////////////////
    public MessageResponse setAddOnDescription(String addId, String description) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(description) || description.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> add =  addOnService.findAddOnById(addId);
        if(add.isEmpty()) throw OptionException.findFail();
        add.get().setDescription(description);
        AddOn addRes = addOnService.updateAddOn(add.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update Description complete");
        res.setRes(addRes);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse deleteAddOn(String addId) throws BaseException {
        Boolean addOn =  addOnService.deleteAddOn(addId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete AddOn complete");
        res.setRes(addOn);
        return res;
    }

    ////////////////////////////////////////


}
