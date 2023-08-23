package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.repository.SettingShopRepository;

import com.example.cafebackend.table.SettingShop;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class SettingShopService {

    private final SettingShopRepository settingShopRepository;

    public SettingShopService(SettingShopRepository settingShopRepository) {
        this.settingShopRepository = settingShopRepository;
    }


    //////////////////////////

    public SettingShop createSetting(Double vat, Double pointSpend, Double pointCollect, LocalTime open, LocalTime close) {
        SettingShop table = new SettingShop();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "SET"+uuid.substring(0, 14);
        table.setSetShopId(uuid);
        table.setVatRate(vat);
        table.setPointSpendRate(pointSpend);
        table.setPointCollectRate(pointCollect);
        table.setOpenDate(open);
        table.setClosedDate(close);
        return settingShopRepository.save(table);
    }
    /////////////////////////

    public SettingShop updateSetting(SettingShop settingShop) throws OptionException {
        /// verify
        if(Objects.isNull(settingShop)) throw OptionException.updateFail();
        /// save
        return settingShopRepository.save(settingShop);
    }
    /////////////////////////

    public Optional<SettingShop> findById(String id){
        ///
        return settingShopRepository.findById(id);
    }
    /////////////////////////

    public List<SettingShop> findAll() {
        ///
        return settingShopRepository.findAll();
    }
    /////////////////////////

    public void deletePointDetail(String pointDetail) {
        ///
        settingShopRepository.deleteById(pointDetail);
    }

    /////////////////////////


}
