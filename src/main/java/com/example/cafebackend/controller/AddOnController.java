package com.example.cafebackend.controller;

import com.example.cafebackend.appString.EString;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.mapper.AddOnMapper;
import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;

import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.AddOnService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.ProductForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AddOnController {

    private AddOnService addOnService;

    private ProductFormService productFormService;

    private AddOnMapper addOnMapper;

    //////////////////////////////////////////////////////////////////////
    public MessageResponse createAddOn(String titleTh, String titleEng, Boolean manyOptions, String description)
            throws BaseException {
        /// validate
        if (Objects.isNull(titleTh) || titleTh.isEmpty())
            throw OptionException.createFail();
        if (Objects.isNull(titleEng) || titleEng.isEmpty())
            titleEng = EString.NONE.getValue();
        if (Objects.isNull(manyOptions))
            manyOptions = false;
        if (Objects.isNull(description) || description.isEmpty())
            description = EString.NONE.getValue();
        /// verify
        if (addOnService.existsByTitleTh(titleTh))
            throw OptionException.updateFailDuplicate();
        if (addOnService.existsByTitleEng(titleEng))
            throw OptionException.updateFailDuplicate();
        /// create
        AddOn add = addOnService.createAddOn(titleTh, titleEng, description, manyOptions);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("create AddOn success");
        res.setRes(add);
        return res;
    }

    public MessageResponse updateAddOn(String addId, String titleTh, String titleEng, Boolean isManyOptions,
            Boolean isEnable, String description) throws BaseException {
        /// validate
        if (Objects.isNull(addId) || addId.isEmpty())
            throw OptionException.findFailRequestNull();
        /// verify
        Optional<AddOn> addOpt = addOnService.findAddOnById(addId);
        if (addOpt.isEmpty())
            throw OptionException.findFail();
        AddOn addOn = addOpt.get();
        /// check title th
        if (!(Objects.isNull(titleTh) || titleTh.isEmpty())) {
            if (!titleTh.equals(addOn.getAddOnTitleTh())) {
                if (addOnService.existsByTitleTh(titleTh))
                    throw OptionException.updateFailDuplicate();
                addOn.setAddOnTitleTh(titleTh);
            }
        }
        /// check title eng
        if (!(Objects.isNull(titleEng) || titleEng.isEmpty())) {
            if (!titleEng.equals(addOn.getAddOnTitleEng())) {
                if (addOnService.existsByTitleEng(titleEng))
                    throw OptionException.updateFailDuplicate();
                addOn.setAddOnTitleEng(titleEng);
            }
        }
        /// check description
        if (!(Objects.isNull(description) || description.isEmpty())) {
            if (!description.equals(addOn.getDescription())) {
                addOn.setDescription(description);
            }
        }
        /// check isEnable
        if (!(Objects.isNull(isEnable))) {
            if (!isEnable.equals(addOn.getIsEnable())) {
                addOn.setIsEnable(isEnable);
            }
        }
        /// check isManyOptions
        if (!(Objects.isNull(isManyOptions))) {
            if (!isManyOptions.equals(addOn.getIsManyOptions())) {
                addOn.setIsManyOptions(isManyOptions);
            }
        }
        /// update addon
        AddOn addRes = addOnService.updateAddOn(addOn);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("update addOn success");
        res.setRes(addRes);
        return res;
    }
    /////////////////////////////////

    public MessageResponse updateAddOnInProductForm(Long fromId, List<AddOn> listAddOnId) throws Exception {
        /// validate
        if (Objects.isNull(fromId))
            throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductForm> product = productFormService.findProductFormById(fromId);
        if (product.isEmpty())
            throw ProductException.findProductFail();
        ProductForm productForm = product.get();
        /// check addOn
        // List<AddOn> addOnList = new ArrayList<>();
        if (!(Objects.isNull(listAddOnId) || listAddOnId.isEmpty())) {
            for (AddOn addId : listAddOnId) {
                Optional<AddOn> addOn = addOnService.findAddOnById(addId.getAddOnId());
                if (addOn.isEmpty()) {
                    throw OptionException.findFail();
                }
                productForm.getAddOn().add(addOn.get());
            }
        }
        /// set addOn
        // productForm.getAddOn().clear();
        // productForm.getAddOn().addAll(addOnList);
        ProductForm prod = productFormService.updateProductForm(productForm);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("add to Product(f) success");
        res.setRes(prod);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse deleteAddOnFromProductForm(Long fromId, List<AddOn> listAddOnId) throws Exception {
        /// validate
        if (Objects.isNull(fromId))
            throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductForm> product = productFormService.findProductFormById(fromId);
        if (product.isEmpty())
            throw ProductException.findProductFail();
        ProductForm productForm = product.get();
        /// check addOn
        // List<AddOn> addOn = productForm.getAddOn();
        if (!(Objects.isNull(listAddOnId) || listAddOnId.isEmpty())) {
            for (AddOn add : listAddOnId) {
                productForm.getAddOn().removeIf((item) -> item.getAddOnId().equals(add.getAddOnId()));
            }
        }
        /// set addOn
        // productForm.setAddOn(addOn);
        ProductForm prod = productFormService.updateProductForm(productForm);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("delete from Product(f) success");
        res.setRes(prod);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findAddOn(String addId, Long formId, String option) throws BaseException {
        /// validate
        /// have add id
        if (!(Objects.isNull(addId) || addId.isEmpty())) {
            Optional<AddOn> add = addOnService.findAddOnById(addId);
            if (add.isEmpty())
                throw OptionException.findFail();
            AddOn addOn = add.get();
            /// if option true
            if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")) {
                ForAddOnResponse addRes = addOnMapper.toForAddOnResponse(addOn);
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get Addon Option By ID");
                res.setRes(addRes);
                return res;
            }
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get AddOn By Id");
            res.setRes(add);
            return res;
        }
        /// have form id
        if (!(Objects.isNull(formId))) {
            Optional<ProductForm> productForm = productFormService.findProductFormById(formId);
            if (productForm.isEmpty())
                throw ProductException.findBaseFail();
            // ProductForm form = productForm.get();
            /// if option true
            if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")) {
                List<AddOn> listAddons = addOnService.findAddOnByProductFormId(formId);
                List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(listAddons);
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get list Addon Option By form ID");
                res.setRes(listAdd);
                return res;
            }
            /// res
            List<AddOn> listAddons = addOnService.findAddOnByProductFormId(formId);
            MessageResponse res = new MessageResponse();
            res.setMessage("get list Addon By form ID");
            res.setRes(listAddons);
            return res;
        }
        List<AddOn> addOnList = addOnService.findListAddOn();
        /// if option true
        if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")) {
            List<ForAddOnResponse> addRes = addOnMapper.toListForAddOnResponse(addOnList);
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get Addon Option By ID");
            res.setRes(addRes);
            return res;
        }
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn All");
        res.setRes(addOnList);
        return res;

    }

    ////////////////////////////////////////////////////////////////

    public MessageResponse deleteAddOn(String addId) throws BaseException {
        /// validate
        if (Objects.isNull(addId) || addId.isEmpty())
            throw ProductException.findProductFail();
        Boolean addOn = addOnService.deleteAddOn(addId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete AddOn success");
        res.setRes(addOn);
        return res;
    }
    ////////////////////////////////////////

}
