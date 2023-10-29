package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.repository.OptionRepository;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.MaterialUsed;
import com.example.cafebackend.table.Option;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    private final MaterialUsedService materialUsedService;

    public OptionService(OptionRepository optionRepository, MaterialUsedService materialUsedService) {
        this.optionRepository = optionRepository;
        this.materialUsedService = materialUsedService;
    }

    //////////////////////////
    public Option createOption(AddOn addOn, String optionNameTh, String optionNameEng, Double price)
            throws BaseException {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "OP" + uuid.substring(0, 13);
        /// save
        Option table = new Option();
        table.setOptionId(uuid);
        table.setOptionNameTh(optionNameTh);
        table.setOptionNameEng(optionNameEng);
        table.setPrice(price);
        table.setIsEnable(true);
        table.setIsMaterialEnable(true);
        table.setAddOn(addOn);
        table.setIsDelete(false);
        return optionRepository.save(table);
    }

    public Optional<Option> findOptionById(String id) {
        ///
        return optionRepository.findOptionById(id);
    }

    public List<Option> findListOption() {
        ///
        return optionRepository.findOptionAll();
    }

    // public Optional<Option> findOptionByAddOnId(String id) {
    // ///
    // return optionRepository.findByAddOnAddOnId(id);
    // }

    public List<Option> findOptionByAddOnId(String id) {
        ///
        return optionRepository.findOptionByAddOnId(id);
    }

    public List<String> findOptionNameThByAddOnId(String name) {
        ///
        return optionRepository.findOptionNameThByAddOnId(name);
    }

    public List<String> findOptionNameEngByAddOnId(String name) {
        ///
        return optionRepository.findOptionNameEngByAddOnId(name);
    }

    public Boolean existsByOptionNameTh(String info) {
        ///
        return optionRepository.existsByOptionNameTh(info);
    }

    public Boolean existsByOptionNameEng(String info) {
        ///
        return optionRepository.existsByOptionNameEng(info);
    }

    public Option updateOption(Option option) throws BaseException {
        /// validate
        if (Objects.isNull(option))
            throw OptionException.updateFail();
        /// save
        return optionRepository.save(option);
    }

    public List<Option> findOptionByMateId(String mateId) {
        ///
        return optionRepository.findOptionByMateId(mateId);
    }

    public Boolean deleteOption(String id) throws BaseException {
        /// verify
        Optional<Option> opt = optionRepository.findById(id);
        Option baseDelete = opt.get();
        List<MaterialUsed> listDeleteMateUse = new ArrayList<>();
        baseDelete.getMaterialUsed().forEach(e -> listDeleteMateUse.add(e));
        for (MaterialUsed mateUse : listDeleteMateUse) {
            materialUsedService.deleteMaterialUsed(mateUse.getMateUsedId());
        }
        optionRepository.deleteById(id);
        Optional<Option> option = optionRepository.findById(id);
        if (option.isEmpty()) {
            return true;
        }
        throw OptionException.deleteFail();

        // Optional<Option> option = optionRepository.findById(id);
        // option.get().setIsDelete(true);
        // option.get().removeFromAddOn();
        // optionRepository.save(option.get());
        // return true;
    }

}
