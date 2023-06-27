package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.AddOnService;
import com.example.cafebackend.service.OptionService;
import com.example.cafebackend.service.ProductFormService;
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
        if(Objects.isNull(price) ) price = 0.0;
        /// verify
        Optional<AddOn> addOnOpt = addOnService.findAddOnById(addId);
        if(addOnOpt.isEmpty()) throw OptionException.findFail();
        AddOn addOn = addOnOpt.get();
        /// check option name
        if(optionService.existsByOptionName(optionName))
            for (Option listOpt : addOn.getOptions()) {
                if (listOpt.getOptionName().equals(optionName)) throw OptionException.createFail();
            }
        Option opt = optionService. createOption(addOn, optionName, price);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Option success");
        res.setRes(opt);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse updateOption(String optionId, String optionName, String price, String isEnable) throws BaseException {
        /// validate
        if(Objects.isNull(optionId) || optionId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(optionName) || optionName.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(price) || price.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> optionOpt =  optionService.findOptionById(optionId);
        if(optionOpt.isEmpty()) throw OptionException.findFail();
        Option option = optionOpt.get();
        /// check option name
        if (optionService.existsByOptionName(optionName)){
            for (Option listOpt : option.getAddOn().getOptions())
                if (listOpt.getOptionName().equals(optionName)) throw OptionException.createFail();
            option.setOptionName(optionName);
        };
        /// check price
        Double setPrice = Double.valueOf(price);
        if(!setPrice.equals(option.getPrice())) {
            option.setPrice(setPrice);
        }
        /// check isEnable
        String enable = String.valueOf(option.getIsEnable());
        if(!isEnable.equals(enable)){
            option.setIsEnable(Boolean.valueOf(isEnable));
        }
        /// res
        Option optRes  = optionService.updateOption(option);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Option success");
        res.setRes(optRes);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findOptionById(String optId) throws BaseException {
        /// validate
        if(Objects.isNull(optId) || optId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> option =  optionService.findOptionById(optId);
        if(option.isEmpty()) throw OptionException.findFail();
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Option By ID complete");
        res.setRes(option);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse findAllOption(){
        MessageResponse res = new MessageResponse();
        /// res
        res.setMessage("get Options success");
        res.setRes(optionService.findListOption());
        return res;
    }
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
