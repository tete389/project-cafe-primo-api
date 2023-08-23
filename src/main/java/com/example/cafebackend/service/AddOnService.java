package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.repository.AddOnRepository;
import com.example.cafebackend.table.AddOn;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddOnService {

    private final AddOnRepository addOnRepository;

    public AddOnService(AddOnRepository addOnRepository) {
        this.addOnRepository = addOnRepository;
    }


    //////////////////////////
    public AddOn createAddOn(String title, String description, Boolean isManyOptions) throws BaseException {
        /// verify
        if(addOnRepository.existsByAddOnTitle(title)) throw OptionException.updateFailDuplicate();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "AD"+uuid.substring(0, 13);
        /// save
        AddOn table = new AddOn();
        table.setAddOnId(uuid);
        table.setAddOnTitle(title);
        table.setIsManyOptions(isManyOptions);
        table.setDescription(description);
        table.setIsEnable(true);
        return addOnRepository.save(table);
    }

    public Optional<AddOn> findAddOnById(String id){
        ///
        return addOnRepository.findById(id);
    }

    public List<AddOn> findListAddOn() {
        ///
        return addOnRepository.findAll();
    }

    public Optional<AddOn> findAddOnByTitle(String name) {
        ///
        return addOnRepository.findByAddOnTitle(name);
    }

    public Boolean existsByTitle(String title) {
        ///
        return addOnRepository.existsByAddOnTitle(title);
    }


    public AddOn updateAddOn(AddOn addOn) throws BaseException {
        /// validate
        if(Objects.isNull(addOn)) throw OptionException.updateFail();
        /// save
        return addOnRepository.save(addOn);
    }

    public Boolean deleteAddOn(String id) throws BaseException {
        /// verify
        addOnRepository.deleteById(id);
        Optional<AddOn> addOn = addOnRepository.findById(id);
        if(addOn.isEmpty()) return true;
        throw OptionException.deleteFail();
    }


}
