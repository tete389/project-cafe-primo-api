package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.SettingShopService;
import com.example.cafebackend.service.TokenService;
import com.example.cafebackend.table.SettingShop;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
        List<SettingShop> shop = shopService.findAll();
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get setting shop");
        res.setRes(shop.get(0));
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse updateSettingShop(String setShopId, Double pointSpendRate, Double pointCollectRate,
            Double vatRate, Boolean isOpen, Boolean isClose, LocalTime openDate, LocalTime closedDate)
            throws BaseException {
        tokenService.checkTokenEmp();
        /// validate
        if (Objects.isNull(setShopId) || setShopId.isEmpty())
            throw EmployeeException.updateFailDataNull();

        /// verify
        Optional<SettingShop> shop = shopService.findById(setShopId);
        if (shop.isEmpty())
            throw EmployeeException.updateFailDataNull();
        SettingShop setting = shop.get();

        /// check isOpenShop
        // String isOpen = setting.getIsOpenShop().toString();
        if (!(Objects.isNull(isOpen))) {
            if (!setting.getIsOpenShop().equals(isOpen))
                setting.setIsOpenShop(isOpen);
        }

        if (!(Objects.isNull(isClose))) {
            if (!setting.getIsCloseShop().equals(isClose))
                setting.setIsCloseShop(isClose);
        }

        /// check spend Rate
        // Double spend = Double.valueOf(pointSpendRate);
        if (!(Objects.isNull(pointSpendRate))) {
            if (!setting.getPointSpendRate().equals(pointSpendRate)) {
                setting.setPointSpendRate(pointSpendRate);
            }
        }

        /// check collect Rate
        // Double collect = Double.valueOf(pointCollectRate);
        if (!(Objects.isNull(pointCollectRate))) {
            if (!setting.getPointCollectRate().equals(pointCollectRate))
                setting.setPointCollectRate(pointCollectRate);
        }

        /// check vat Rate
        // Double vat = Double.valueOf(vatRate);
        if (!(Objects.isNull(vatRate))) {
            if (!setting.getVatRate().equals(vatRate))
                setting.setVatRate(vatRate);
        }

        /// check openDate
        // LocalTime open = LocalTime.parse(openDate);
        // LocalTime openTimes =
        /// LocalTime.parse(open.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        if (!(Objects.isNull(openDate))) {
            if (!setting.getOpenDate().equals(openDate))
                setting.setOpenDate(openDate);
        }

        /// check closeDate
        // LocalTime close = LocalTime.parse(closedDate);
        // LocalTime closeTimes =
        /// LocalTime.parse(close.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        if (!(Objects.isNull(closedDate))) {
            if (!setting.getClosedDate().equals(closedDate))
                setting.setClosedDate(closedDate);
        }

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
