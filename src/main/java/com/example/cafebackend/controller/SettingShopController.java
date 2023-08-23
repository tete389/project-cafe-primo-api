package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.SettingShopService;
import com.example.cafebackend.service.TokenService;
import com.example.cafebackend.table.Employee;
import com.example.cafebackend.table.SettingShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SettingShopController {

    private SettingShopService shopService;

    private TokenService tokenService;

    //////////////////////////////////////////////////////////////////////
    public MessageResponse getSettingShop() throws BaseException {
        /// check setting
        List<SettingShop> shop  = shopService.findAll();
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get setting shop");
        res.setRes(shop.get(0));
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse updateSettingShop(String setShopId, String pointSpendRate, String pointCollectRate, String vatRate ,String openDate, String closedDate) throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        /// validate
        if(Objects.isNull(setShopId) || setShopId.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(pointSpendRate) || pointSpendRate.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(pointCollectRate) || pointCollectRate.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(vatRate) || vatRate.isEmpty()) throw EmployeeException.updateFailDataNull();
        //if(Objects.isNull(isOpenShop) || isOpenShop.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(openDate) || openDate.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(closedDate) || closedDate.isEmpty()) throw EmployeeException.updateFailDataNull();
        /// verify
        Optional<SettingShop> shop =  shopService.findById(setShopId);
        if(shop.isEmpty()) throw EmployeeException.updateFailDataNull();
        SettingShop setting = shop.get();
        /// check spend Rate
        Double spend =  Double.valueOf(pointSpendRate);
        if (!setting.getPointSpendRate().equals(spend)) setting.setPointSpendRate(spend);
        /// check collect Rate
        Double collect =  Double.valueOf(pointCollectRate);
        if (!setting.getPointCollectRate().equals(collect)) setting.setPointCollectRate(collect);
        /// check vat Rate
        Double vat =  Double.valueOf(vatRate);
        if (!setting.getVatRate().equals(vat)) setting.setVatRate(vat);
//        /// check isOpenShop
//        String isOpen =  setting.getIsOpenShop().toString();
//        if (!isOpen.equals(isOpenShop)) setting.setIsOpenShop(!setting.getIsOpenShop());
        /// check openDate
        LocalTime open = LocalTime.parse(openDate);
        LocalTime openTimes = LocalTime.parse(open.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        if (!setting.getOpenDate().equals(openTimes)) setting.setOpenDate(openTimes);
        /// check closeDate
        LocalTime close = LocalTime.parse(closedDate);
        LocalTime closeTimes = LocalTime.parse(close.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        if (!setting.getOpenDate().equals(closeTimes)) setting.setOpenDate(closeTimes);
        /// update setting
        SettingShop shopRes = shopService.updateSetting(setting);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("update setting success");
        res.setRes(shopRes);
        return res;
    }
    ////////////////////////////////////////




}
