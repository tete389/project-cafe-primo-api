package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.repository.AddOnRepository;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Option;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddOnService {

    private final AddOnRepository addOnRepository;

    private final OptionService optionService;

    public AddOnService(AddOnRepository addOnRepository, OptionService optionService) {
        this.addOnRepository = addOnRepository;
        this.optionService = optionService;
    }

    //////////////////////////
    public AddOn createAddOn(String titleTh, String titleEng, String description, Boolean isManyOptions)
            throws BaseException {
        /// verify
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "AD" + uuid.substring(0, 13);
        /// save
        AddOn table = new AddOn();
        table.setAddOnId(uuid);
        table.setAddOnTitleTh(titleTh);
        table.setAddOnTitleEng(titleEng);
        table.setIsManyOptions(isManyOptions);
        table.setDescription(description);
        table.setIsEnable(true);
        table.setIsDelete(false);
        return addOnRepository.save(table);
    }

    public Optional<AddOn> findAddOnById(String id) {
        ///
        return addOnRepository.findAddOnById(id);
    }

    public List<AddOn> findListAddOn() {
        ///
        return addOnRepository.findAddOnAll();
    }

    public Optional<AddOn> findAddOnByTitleTh(String name) {
        ///
        return addOnRepository.findByAddOnTitleTh(name);
    }

    public Optional<AddOn> findAddOnByTitleEng(String name) {
        ///
        return addOnRepository.findByAddOnTitleEng(name);
    }

    public List<AddOn> findAddOnByProductFormId(Long id) {
        ///
        return addOnRepository.findAddOnByProductFormId(id);
    }

    public Boolean existsByTitleTh(String title) {
        ///
        return addOnRepository.existsByAddOnTitleTh(title);
    }

    public Boolean existsByTitleEng(String title) {
        ///
        return addOnRepository.existsByAddOnTitleEng(title);
    }

    public AddOn updateAddOn(AddOn addOn) throws BaseException {
        /// validate
        if (Objects.isNull(addOn))
            throw OptionException.updateFail();
        /// save
        return addOnRepository.save(addOn);
    }

    public Boolean deleteAddOn(String id) throws BaseException {
        /// verify
        Optional<AddOn> addOnOpt = addOnRepository.findById(id);
        AddOn addOnDelete = addOnOpt.get();

        List<Option> listDeleteOption = new ArrayList<>();
        addOnDelete.getOptions().forEach(e -> listDeleteOption.add(e));
        for (Option option : listDeleteOption) {
            optionService.deleteOption(option.getOptionId());
        }

        addOnRepository.deleteById(id);

        Optional<AddOn> addOn = addOnRepository.findById(id);
        // addOn.get().setIsDelete(true);
        // addOnRepository.save(addOn.get());
        // return true;

        if (addOn.isEmpty()) {
            return true;
        }
        throw OptionException.deleteFail();
    }

}
