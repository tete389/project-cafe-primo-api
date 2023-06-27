package com.example.cafebackend.controller;

import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.AddOnMapper;
import com.example.cafebackend.mapper.MaterialMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.request.MateUsedRequest;
import com.example.cafebackend.model.response.*;
import com.example.cafebackend.model.response.ForFind.ForFindAddOnInProdFormResponse;
import com.example.cafebackend.model.response.ForFind.ForFindMateUseInOptionResponse;
import com.example.cafebackend.model.response.ForFind.ForFindMateUseInProdBaseResponse;
import com.example.cafebackend.model.response.ForFind.ForFindMateUseInProdFormResponse;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
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

    private AddOnMapper addOnMapper;

    //////////////////////////////////////////////////////////////////////

    public MessageResponse updateAddMaterialUsedInBase(String baseId, List<MateUsedRequest> request) throws Exception{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw MaterialException.updateFail();
        //// verify
        /// check product form
        Optional<ProductBase> prodOpt = productBaseService.findBaseById(baseId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductBase productBase = prodOpt.get();
        /// check material  to save
        List<MaterialUsed> listSaveMateUse = new ArrayList<>();
        manageMaterial(request, listSaveMateUse, productBase, null, null);
        /// response
        productBase.setMaterialUsed(listSaveMateUse);
        MessageResponse res = new MessageResponse();
        res.setMessage("update MaterialUsed success");
        res.setRes(productBase);
        return res;
    }
    //////////////////////////////////////

    public MessageResponse updateAddMaterialUsedInForm(String formId, List<MateUsedRequest> request) throws Exception{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw MaterialException.updateFail();
        //// verify
        /// check product base
        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductForm productForm = prodOpt.get();
        /// check save material use
        List<MaterialUsed> listSaveMateUse = new ArrayList<>();
        manageMaterial(request, listSaveMateUse, null, productForm, null);
        /// response
        productForm.setMaterialUsed(listSaveMateUse);
        MessageResponse res = new MessageResponse();
        res.setMessage("update MaterialUsed success");
        res.setRes(productForm);
        return res;
    }
    //////////////////////////////////////

    public MessageResponse updateAddMaterialUsedInOption(String optionId, List<MateUsedRequest> request) throws Exception{
        /// validate
        if(Objects.isNull(optionId) || optionId.isEmpty()) throw MaterialException.updateFail();
        //// verify
        /// check product form
        Optional<Option> opt = optionService.findOptionById(optionId);
        if(opt.isEmpty()) throw ProductException.findProductFail();
        Option option = opt.get();
        /// check material to save
        List<MaterialUsed> listSaveMateUse = new ArrayList<>();
        manageMaterial(request, listSaveMateUse,null, null, option);
        /// response
        option.setMaterialUsed(listSaveMateUse);
        MessageResponse res = new MessageResponse();
        res.setMessage("update MaterialUsed success");
        res.setRes(option);
        return res;
    }
    //////////////////////////////////////

    public MessageResponse findMaterialUsedInBaseId(String baseId) throws Exception{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw MaterialException.findFail();
        //// verify
        /// check base
        Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
        if(baseOpt.isEmpty()) throw ProductException.findProductFail();
        ProductBase productBase = baseOpt.get();
        /// check res material use
        List<MaterialUsedNec> ListMateNec = new ArrayList<>();
        for (MaterialUsed mateUsed : productBase.getMaterialUsed()){
            MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed, mateUsed.getMaterial());
            ListMateNec.add(materialUsedNec);
        }
         ForFindMateUseInProdBaseResponse baseRes =  productMapper.toForFindMateUseInProdBaseResponse(productBase, ListMateNec);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get MaterialUsed success");
        res.setRes(baseRes);
        return res;
    }
    //////////////////////////////////////

    public MessageResponse findMaterialUsedInFormId(String formId) throws Exception{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw MaterialException.findFail();
        //// verify
        /// check form
        Optional<ProductForm> formOpt = productFormService.findProductFormById(formId);
        if(formOpt.isEmpty()) throw ProductException.findProductFail();
        ProductForm productForm = formOpt.get();
        /// check res material use
        List<MaterialUsedNec> ListMateNec = new ArrayList<>();
        for (MaterialUsed mateUsed : productForm.getMaterialUsed()){
            MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed, mateUsed.getMaterial());
            ListMateNec.add(materialUsedNec);
        }
        ForFindMateUseInProdFormResponse baseRes =  productMapper.toForFindMateUseInProdFormResponse(productForm, ListMateNec);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get MaterialUsed success");
        res.setRes(baseRes);
        return res;
    }
    //////////////////////////////////////

    public MessageResponse findMaterialUsedInOptionId(String optionId) throws Exception{
        /// validate
        if(Objects.isNull(optionId) || optionId.isEmpty()) throw MaterialException.findFail();
        //// verify
        /// check form
        Optional<Option> optionOpt = optionService.findOptionById(optionId);
        if(optionOpt.isEmpty()) throw ProductException.findProductFail();
        Option option = optionOpt.get();
        /// check res material use
        List<MaterialUsedNec> ListMateNec = new ArrayList<>();
        for (MaterialUsed mateUsed : option.getMaterialUsed()){
            MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed, mateUsed.getMaterial());
            ListMateNec.add(materialUsedNec);
        }
        ForFindMateUseInOptionResponse optionRes =  addOnMapper.toForFindMateUseInOptionResponse(option, ListMateNec);
        /// response
        MessageResponse res = new MessageResponse();
        res.setMessage("get MaterialUsed success");
        res.setRes(optionRes);
        return res;
    }
    //////////////////////////////////////

    public void manageMaterial(List<MateUsedRequest> request,  List<MaterialUsed> listSaveMateUse, ProductBase base, ProductForm form, Option option) throws Exception {
        /// check material to save
        for (MateUsedRequest usedRequest : request) {
            Optional<Material> material = materialService.findById(usedRequest.getMateId());
            if(material.isEmpty()) throw MaterialException.findFail();
            MaterialUsed used = new MaterialUsed();
            if(base != null) {
                Optional<MaterialUsed> mateUsed = materialUsedService.findByBaseIdAndMateId(base.getProdBaseId(), material.get().getMateId());
                if (mateUsed.isPresent()) used =  mateUsed.get();
                used.setMaterial(material.get());
                used.setProductBase(base);
                used.setIsEnable(material.get().getIsEnable());
                used.setAmountUsed(Double.valueOf(usedRequest.getUseCount()));
                listSaveMateUse.add(used);
            } else if (form != null) {
                Optional<MaterialUsed> mateUsed = materialUsedService.findByFormIdAndMateId(form.getProdFormId(), material.get().getMateId());
                if (mateUsed.isPresent()) used =  mateUsed.get();
                used.setMaterial(material.get());
                used.setProductForm(form);
                used.setIsEnable(material.get().getIsEnable());
                used.setAmountUsed(Double.valueOf(usedRequest.getUseCount()));
                listSaveMateUse.add(used);
            } else if (option != null) {
                Optional<MaterialUsed> mateUsed = materialUsedService.findByOptionIdAndMateId(option.getOptionId(), material.get().getMateId());
                if (mateUsed.isPresent()) used =  mateUsed.get();
                used.setMaterial(material.get());
                used.setOption(option);
                used.setIsEnable(material.get().getIsEnable());
                used.setAmountUsed(Double.valueOf(usedRequest.getUseCount()));
                listSaveMateUse.add(used);
            }
        }
        /// check clear material use
        List<String> listClearMateUse = new ArrayList<>();
        List<MaterialUsed> usedList = new ArrayList<>();
        if(base != null) usedList =  base.getMaterialUsed();
         else if (form != null) usedList =  form.getMaterialUsed();
         else if (option != null) usedList =  option.getMaterialUsed();

        for (MaterialUsed clear : usedList) {
            boolean oldData = false;
            for (MaterialUsed list : listSaveMateUse) {
                if ((list.getMateUsedId() != null) ) {
                    if(list.getMateUsedId().equals(clear.getMateUsedId())){
                        oldData = true;
                        break;
                    }
                }
            }
            if (!oldData) listClearMateUse.add(clear.getMateUsedId());
        }
        /// clear material use
        for (String useMateId : listClearMateUse) materialUsedService.deleteMaterialUsed(useMateId);
        /// save material use
        for (MaterialUsed save : listSaveMateUse) materialUsedService.updateMaterialUsed(save);
    }
    //////////////////////////////////////


    // Utility method to get the root cause of an exception
    public static Throwable getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }


}
