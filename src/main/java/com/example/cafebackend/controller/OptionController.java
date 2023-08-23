package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.AddOnService;
import com.example.cafebackend.service.OptionService;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OptionController {

    private OptionService optionService;

    private AddOnService addOnService;

    //////////////////////////////////////////////////////////////////////
    public MessageResponse createOption(String addId, String optionName, Double price) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.createFail();
        if(Objects.isNull(optionName) || optionName.isEmpty()) throw OptionException.createFail();
        if(Objects.isNull(price)) price = 0.0;
        /// verify
        Optional<AddOn> addOnOpt = addOnService.findAddOnById(addId);
        if(addOnOpt.isEmpty()) throw OptionException.findFail();
        AddOn addOn = addOnOpt.get();
        /// check option name
        if(optionService.existsByOptionName(optionName)) {
            for (Option listOpt : addOn.getOptions()) {
                if (listOpt.getOptionName().equals(optionName)) throw OptionException.createFail();
            }
        }
        Option opt = optionService.createOption(addOn, optionName, price);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Option success");
        res.setRes(opt);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse updateOption(String optionId, String optionName, Double price, Boolean isEnable) throws BaseException {
        /// validate
        if(Objects.isNull(optionId) || optionId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> optionOpt =  optionService.findOptionById(optionId);
        if(optionOpt.isEmpty()) throw OptionException.findFail();
        Option option = optionOpt.get();
        /// check option name
        if (!(Objects.isNull(optionName) || optionName.isEmpty())){
            if (!optionName.equals(option.getOptionName())){
                for (String listOpt : optionService.findOptionNameByAddOnId(option.getAddOn().getAddOnId())) {
                    if (listOpt.equals(optionName)) throw OptionException.createFail();
                }
                option.setOptionName(optionName);
            }
        }
        /// check price
       // Double setPrice = Double.valueOf(price);
        if (!(Objects.isNull(price))) {
            if(!price.equals(option.getPrice())) {
                option.setPrice(price);
            }
        }
        /// check isEnable
        //String enable = String.valueOf(option.getIsEnable());
        if (!(Objects.isNull(isEnable))) {
            if(!isEnable.equals(option.getIsEnable())){
                option.setIsEnable(isEnable);
            }
        }
        /// res
        Option optRes  = optionService.updateOption(option);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Option success");
        res.setRes(optRes);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findOption(String optId, String addId) throws BaseException {
        /// validate
        /// have optId
        if(!(Objects.isNull(optId) || optId.isEmpty())) {
            Optional<Option> option =  optionService.findOptionById(optId);
            if(option.isEmpty()) throw OptionException.findFail();
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get Option By ID");
            res.setRes(option);
            return res;
        };
        /// have addId
        if(!(Objects.isNull(addId) || addId.isEmpty())) {
            Optional<AddOn> addOn =  addOnService.findAddOnById(addId);
            if(addOn.isEmpty()) throw ProductException.findBaseFail();
            AddOn add = addOn.get();
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get Option By addOn ID");
            res.setRes(add.getOptions());
            return res;
        };
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Options All");
        res.setRes(optionService.findListOption());
        return res;
    }




    ////////////
//    public MessageResponse findAllOption(){
//        MessageResponse res = new MessageResponse();
//        /// res
//        res.setMessage("get Options All");
//        res.setRes(optionService.findListOption());
//        return res;
//    }
    ////////////////////////////////////////

//    public MessageResponse findOptionByAddOnId(String addId) throws BaseException {
//        /// validate
//        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
//        /// verify
//        Optional<AddOn> add =  addOnService.findAddOnById(addId);
//        if(add.isEmpty()) throw OptionException.findFail();
//        //ForAddOnResponse addRes = addOnMapper.toForAddOnResponse(add.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Options by Addon ID ");
//        res.setRes(add.get().getOptions());
//        return res;
//    }
    ////////////////////////////////////////

//    public MessageResponse findOptionInfoByAddOnId(String addId) throws BaseException {
//        /// validate
//        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.findFailRequestNull();
//        /// verify
//        Optional<AddOn> add =  addOnService.findAddOnById(addId);
//        if(add.isEmpty()) throw OptionException.findFail();
//        List<ForOptionInfoResponse> response = addOnMapper.toListForOptionInfoResponse(add.get().getOptions());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get list Options info by Addon ID ");
//        res.setRes(response);
//        return res;
//    }
    ////////////////////////////////////////

    public MessageResponse deleteOption(String optId) throws BaseException {
        Boolean opt =  optionService.deleteOption(optId);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Option success");
        res.setRes(opt);
        return res;
    }

    ////////////////////////////////////////


}
