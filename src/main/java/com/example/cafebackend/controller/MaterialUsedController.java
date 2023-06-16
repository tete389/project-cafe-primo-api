package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.MaterialMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.request.MateUsedRequest;
import com.example.cafebackend.model.request.UsedRequest;
import com.example.cafebackend.model.response.ForMaterialResponse;
import com.example.cafebackend.model.response.ForProductOnlyResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaterialUsedController {

    private MaterialService materialService;

    private MaterialUsedService materialUsedService;

    private ProductFormService productFormService;

    private ProductBaseService productBaseService;

    private OptionService optionService;

    private MaterialMapper materialMapper;

    private ProductMapper productMapper;

    //////////////////////////////////////////////////////////////////////

    public MessageResponse updateAddMaterialUsedToBase(String baseId, List<MateUsedRequest> request) throws Exception{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw MaterialException.updateFail();
        //// verify
        /// check product form
        Optional<ProductBase> prodOpt = productBaseService.findBaseById(baseId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductBase productBase = prodOpt.get();
        /// check material
        List<MaterialUsed> listMateUse = new ArrayList<>();
        for (MateUsedRequest usedRequest : request) {
            Optional<Material> material = materialService.findById(usedRequest.getMateId());
            if(material.isEmpty()) throw MaterialException.findFail();
            MaterialUsed used = new MaterialUsed();
            Optional<MaterialUsed> mateUsed = materialUsedService.findByBaseIdAndMateId(productBase.getProdBaseId(), material.get().getMateId());
            if (mateUsed.isPresent()) used =  mateUsed.get();
            used.setMaterial(material.get());
            used.setProductBase(productBase);
            used.setUsedCount(Double.valueOf(usedRequest.getUseCount()));
            listMateUse.add(used);
        }
        /// clear
        for (MaterialUsed clear : productBase.getMaterialUsed()) {
            boolean oldData = false;
            for (MaterialUsed list : listMateUse) {
                if (list.getMateUsedId().equals(clear.getMateUsedId())) {
                    oldData = true;
                    break;
                }
            }
            if (!oldData) materialUsedService.deleteMaterialUsed(clear.getMateUsedId());
        }
        /// save
        for (MaterialUsed save : listMateUse){
            boolean oldData = false;
            for (MaterialUsed list : listMateUse) {
                if (list.getMateUsedId().equals(save.getMateUsedId())) {
                    oldData = true;
                    break;
                }
            }
            if (!oldData) materialUsedService.updateMaterialUsed(save);
        }
        /// response
        productBase.setMaterialUsed(listMateUse);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Material used success");
        res.setRes(productBase);
        return res;
    }

    public MessageResponse updateAddMaterialUsedToForm(String formId, List<MateUsedRequest> request) throws Exception{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw MaterialException.updateFail();
        //// verify
        /// check product form
        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductForm productForm = prodOpt.get();
        /// check material
        List<MaterialUsed> listMateUse = new ArrayList<>();
        for (MateUsedRequest usedRequest : request) {
            Optional<Material> material = materialService.findById(usedRequest.getMateId());
            if(material.isEmpty()) throw MaterialException.findFail();
            MaterialUsed used = new MaterialUsed();
            Optional<MaterialUsed> mateUsed = materialUsedService.findByFormIdAndMateId(productForm.getProdFormId(), material.get().getMateId());
            if (mateUsed.isPresent()) used =  mateUsed.get();
            used.setMaterial(material.get());
            used.setProductForm(productForm);
            used.setUsedCount(Double.valueOf(usedRequest.getUseCount()));
            listMateUse.add(used);
        }
        /// clear
        for (MaterialUsed clear : productForm.getMaterialUsed()) {
            boolean oldData = false;
            for (MaterialUsed list : listMateUse) {
                if (list.getMateUsedId().equals(clear.getMateUsedId())) {
                    oldData = true;
                    break;
                }
            }
            if (!oldData) materialUsedService.deleteMaterialUsed(clear.getMateUsedId());
        }
        /// save
        for (MaterialUsed save : listMateUse){
            boolean oldData = false;
            for (MaterialUsed list : listMateUse) {
                if (list.getMateUsedId().equals(save.getMateUsedId())) {
                    oldData = true;
                    break;
                }
            }
            if (!oldData) materialUsedService.updateMaterialUsed(save);
        }
//        productForm.getMaterialUsed().clear();
//        productForm.getMaterialUsed().addAll(listMateUse);
        /// response
        //productForm.setMaterialUsed(listMateUse);
        productForm.setMaterialUsed(listMateUse);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Material used success");
        res.setRes(productForm);
        return res;
    }

    public MessageResponse updateAddMaterialUsedToOption(String optionId, List<MateUsedRequest> request) throws Exception{
        /// validate
        if(Objects.isNull(optionId) || optionId.isEmpty()) throw MaterialException.updateFail();
        //// verify
        /// check product form
        Optional<Option> opt = optionService.findOptionById(optionId);
        if(opt.isEmpty()) throw ProductException.findProductFail();
        Option option = opt.get();
        /// check material
        List<MaterialUsed> listMateUse = new ArrayList<>();
        for (MateUsedRequest usedRequest : request) {
            Optional<Material> material = materialService.findById(usedRequest.getMateId());
            if(material.isEmpty()) throw MaterialException.findFail();
            MaterialUsed used = new MaterialUsed();
            Optional<MaterialUsed> mateUsed = materialUsedService.findByOptionIdAndMateId(option.getOptionId(), material.get().getMateId());
            if (mateUsed.isPresent()) used =  mateUsed.get();
            used.setMaterial(material.get());
            used.setOption(option);
            used.setUsedCount(Double.valueOf(usedRequest.getUseCount()));
            listMateUse.add(used);
        }
        /// clear
        for (MaterialUsed clear : option.getMaterialUsed()) {
            boolean oldData = false;
            for (MaterialUsed list : listMateUse) {
                if (list.getMateUsedId().equals(clear.getMateUsedId())) {
                    oldData = true;
                    break;
                }
            }
            if (!oldData) materialUsedService.deleteMaterialUsed(clear.getMateUsedId());
        }
        /// save
        for (MaterialUsed save : listMateUse){
            boolean oldData = false;
            for (MaterialUsed list : listMateUse) {
                if (list.getMateUsedId().equals(save.getMateUsedId())) {
                    oldData = true;
                    break;
                }
            }
            if (!oldData) materialUsedService.updateMaterialUsed(save);
        }
        /// response
        option.setMaterialUsed(listMateUse);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Material used success");
        res.setRes(option);
        return res;
    }

}
