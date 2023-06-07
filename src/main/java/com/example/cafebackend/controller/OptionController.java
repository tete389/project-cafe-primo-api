package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.AddOnService;
import com.example.cafebackend.service.OptionService;
import com.example.cafebackend.service.ProductService;
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

    private ProductService productService;

    //////////////////////////////////////////////////////////////////////
    public MessageResponse createOption(String addId, String optionName, Double price) throws BaseException {
        /// validate
        if(Objects.isNull(addId) || addId.isEmpty()) throw OptionException.createFail();
        if(Objects.isNull(optionName) || optionName.isEmpty()) throw OptionException.createFail();
        if(Objects.isNull(price) ) price = 0.0;
        /// verify
        Optional<AddOn> addOn = addOnService.findAddOnById(addId);
        if(addOn.isEmpty()) throw OptionException.findFail();
        Option opt = optionService.createOption(addOn.get(), optionName, price);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Option complete");
        res.setRes(opt);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse findOptionById(String optId) throws BaseException {
        /// validate
        if(Objects.isNull(optId) || optId.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> option =  optionService.findOptionById(optId);
        if(option.isEmpty()) throw OptionException.findFail();
        MessageResponse res = new MessageResponse();
        res.setMessage("get Option By ID complete");
        res.setRes(option);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse findAllOption(){
        MessageResponse res = new MessageResponse();
        res.setMessage("get Options complete");
        res.setRes(optionService.findListOption());
        return res;
    }


    ////////////////////////////////////////

    public MessageResponse setOptionInfo(String optId, String optionName) throws BaseException {
        /// validate
        if(Objects.isNull(optId) || optId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(optionName) || optionName.isEmpty()) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> option =  optionService.findOptionById(optId);
        if(option.isEmpty()) throw OptionException.findFail();
        if (optionService.existsByOptionName(optionName)){
            Optional<AddOn> addOn = addOnService.findAddOnById(option.get().getAddOn().getAddOnId());
            if (addOn.isEmpty()) throw OptionException.findFail();
            for (Option listOpt : addOn.get().getOptions())
                if (listOpt.getOptionName().equals(optionName)) throw OptionException.createFail();
        };
        option.get().setOptionName(optionName);
        Option optRes  = optionService.updateOption(option.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update Option Info complete");
        res.setRes(optRes);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse setOptionPrice(String optId, Double price) throws BaseException {
        /// validate
        if(Objects.isNull(optId) || optId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(price)) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> option =  optionService.findOptionById(optId);
        if(option.isEmpty()) throw OptionException.findFail();
        option.get().setPrice(price);
        Option optRes = optionService.updateOption(option.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update Option Price complete");
        res.setRes(optRes);
        return res;
    }

    ////////////////////////////////////////
    public MessageResponse setForSale(String optId, Boolean forSale) throws BaseException {
        /// validate
        if(Objects.isNull(optId) || optId.isEmpty()) throw OptionException.findFailRequestNull();
        if(Objects.isNull(forSale)) throw OptionException.findFailRequestNull();
        /// verify
        Optional<Option> option =  optionService.findOptionById(optId);
        if(option.isEmpty()) throw OptionException.findFail();
        option.get().setIsForSale(forSale);
        Option optRes = optionService.updateOption(option.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update ForSale complete");
        res.setRes(optRes);
        return res;
    }


    ////////////////////////////////////////
    public MessageResponse deleteOption(String optId) throws BaseException {
        Boolean opt =  optionService.deleteOption(optId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Option complete");
        res.setRes(opt);
        return res;
    }

    ////////////////////////////////////////


}
