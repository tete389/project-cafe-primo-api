package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.OptionRepository;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Option;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }


    //////////////////////////
    public Option createOption(AddOn addOn, String optionName, Double price) throws BaseException {
        /// verify
        if(optionRepository.existsByOptionName(optionName)) for (Option listOpt : addOn.getOptions())
            if (listOpt.getOptionName().equals(optionName)) throw OptionException.createFail();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        String n1 = String.valueOf(1000 + now.get(Calendar.SECOND) * now.get(Calendar.MINUTE));
        String n2 = String.valueOf(100 + now.get(Calendar.SECOND)+ now.get(Calendar.MINUTE));
        String Id = "Op"+n1+n2;
        /// save
        Option table = new Option();
        table.setOptionId(Id);
        table.setOptionName(optionName);
        table.setPrice(price);
        table.setIsForSale(true);
        table.setAddOn(addOn);
        return optionRepository.save(table);
    }

    public Optional<Option> findOptionById(String id){
        ///
        return optionRepository.findById(id);
    }

    public List<Option> findListOption() {
        ///
        return optionRepository.findAll();
    }

    public Optional<Option> findOptionByAddOnId(String id) {
        ///
        return optionRepository.findByAddOnAddOnId(id);
    }

    public Boolean existsByOptionName(String info) {
        ///
        return optionRepository.existsByOptionName(info);
    }

    public Option updateOption(Option option) throws BaseException {
        /// validate
        if(Objects.isNull(option)) throw OptionException.updateFail();
        /// save
        return optionRepository.save(option);
    }

    public Boolean deleteOption(String id) throws BaseException {
        /// verify
        optionRepository.deleteById(id);
        Optional<Option> option = optionRepository.findById(id);
        if(option.isEmpty()) return true;
        throw OptionException.deleteFail();
    }


}
