package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaterialController {

    private MaterialService materialService;

    private MaterialUsedService materialUsedService;

    private ProductBaseService productBaseService;

    private ProductFormService productFormService;

    private OptionService optionService;

    //////////////////////////////////////////////////////////////////////

    public MessageResponse createMaterial(String mateName, String unit, Double mateStock) throws BaseException {
        /// validate
        if (Objects.isNull(mateName) || mateName.isEmpty())
            throw MaterialException.createFailRequestNull();
        if (Objects.isNull(unit) || unit.isEmpty())
            throw MaterialException.createFailRequestNull();
        if (Objects.isNull(mateStock))
            throw MaterialException.createFailRequestNull();
        /// verify
        Material mate = materialService.createMaterial(mateName, mateStock, unit);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Material success");
        res.setRes(mate);
        return res;

    }
    ////////////////////////////////////////

    public MessageResponse updateMaterial(String mateId, String mateName, Double mateStock, Boolean isEnable,
            String unit) throws BaseException {
        /// validate
        if (Objects.isNull(mateId) || mateId.isEmpty())
            throw MaterialException.findFailRequestNull();
        /// verify
        Optional<Material> mate = materialService.findById(mateId);
        if (mate.isEmpty())
            throw MaterialException.findFail();
        Material material = mate.get();
        /// check mate name
        if (!(Objects.isNull(mateName) || mateName.isEmpty())) {
            if (!mateName.equals(material.getMateName())) {
                if (materialService.existsByName(mateName))
                    throw MaterialException.updateFail();
                material.setMateName(mateName);
            }
        }
        /// check stock
        // Double stock = Double.valueOf(mateStock);
        if (!Objects.isNull(mateStock)) {
            if (!mateStock.equals(material.getStock())) {
                material.setStock(mateStock);
            }
        }
        /// check isEnable
        // String enable = String.valueOf(material.getIsEnable());
        if (!Objects.isNull(isEnable)) {
            if (!isEnable.equals(material.getIsEnable())) {
                material.setIsEnable(isEnable);
                checkMaterialUsed(material);
            }
        }
        /// check unit
        if (!(Objects.isNull(unit) || unit.isEmpty())) {
            if (!unit.equals(material.getMateUnit())) {
                material.setMateUnit(unit);
            }
        }

        ///
        Material mateRes = materialService.updateMaterial(material);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Material success");
        res.setRes(mateRes);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse getMaterial(String mateId) throws BaseException {
        /// validate
        if (!(Objects.isNull(mateId) || mateId.isEmpty())) {
            Optional<Material> mate = materialService.findById(mateId);
            if (mate.isEmpty())
                throw MaterialException.findFail();
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get Material By ID");
            res.setRes(mate);
            return res;
        }
        ;
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Material All");
        res.setRes(materialService.findAllMateASC());
        return res;
    }

    ////////////////////////////////////////

    public String countMaterialLowStock() {
        Integer count = materialService.findMateLowStock();

        return count.toString();
    }

    ///////////////////////////////////////

    public MessageResponse deleteMate(String mateId) throws BaseException {
        /// validate
        if (Objects.isNull(mateId) || mateId.isEmpty())
            throw MaterialException.findFail();
        Boolean mate = materialService.deleteMaterial(mateId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Material success");
        res.setRes(mate);
        return res;
    }
    ////////////////////////////////////////

    ////////////////////////////////////////
    public void checkMaterialUsed(Material material) throws BaseException {

        for (MaterialUsed materialUsed : material.getMaterialUsed()) {
            materialUsed.setIsEnable(material.getIsEnable());
            materialUsedService.updateMaterialUsed(materialUsed);
        }

        List<ProductBase> listProductBase = productBaseService.findBaseByMateId(material.getMateId());
        for (ProductBase base : listProductBase) {
            if (material.getIsEnable().equals(true)) {
                for (MaterialUsed mu : base.getMaterialUsed()) {
                    if (mu.getIsEnable().equals(false)) {
                        base.setIsMaterialEnable(false);
                        break;
                    }
                    base.setIsMaterialEnable(true);
                }
                productBaseService.updateProductBase(base);
            } else {
                base.setIsMaterialEnable(false);
                productBaseService.updateProductBase(base);
            }
        }

        List<ProductForm> listProductForm = productFormService.findFormByMateId(material.getMateId());
        for (ProductForm form : listProductForm) {
            if (material.getIsEnable().equals(true)) {
                for (MaterialUsed mu : form.getMaterialUsed()) {
                    if (mu.getIsEnable().equals(false)) {
                        form.setIsMaterialEnable(false);
                        break;
                    }
                    form.setIsMaterialEnable(true);
                }
                productFormService.updateProductForm(form);
            } else {
                form.setIsMaterialEnable(false);
                productFormService.updateProductForm(form);
            }
        }

        List<Option> listOption = optionService.findOptionByMateId(material.getMateId());
        for (Option option : listOption) {
            if (material.getIsEnable().equals(true)) {
                for (MaterialUsed mu : option.getMaterialUsed()) {
                    if (mu.getIsEnable().equals(false)) {
                        option.setIsEnable(false);
                        break;
                    }
                    option.setIsMaterialEnable(true);
                }
                optionService.updateOption(option);
            } else {
                option.setIsMaterialEnable(false);
                optionService.updateOption(option);
            }
        }

    }

}
