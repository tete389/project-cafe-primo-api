package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
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

        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "OP"+uuid.substring(0, 13);
        /// save
        Option table = new Option();
        table.setOptionId(uuid);
        table.setOptionName(optionName);
        table.setPrice(price);
        table.setIsEnable(true);
        table.setIsMaterialEnable(true);
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

    public List<String> findOptionNameByAddOnId(String name) {
        ///
        return optionRepository.findOptionNameByAddOnId(name);
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

    public List<Option> findOptionByMateId(String mateId) {
        ///
        return optionRepository.findOptionByMateId(mateId);
    }

    public Boolean deleteOption(String id) throws BaseException {
        /// verify
        optionRepository.deleteById(id);
        Optional<Option> option = optionRepository.findById(id);
        if(option.isEmpty()) return true;
        throw OptionException.deleteFail();
    }


}
