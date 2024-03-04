package com.example.cafebackend.controller;

import com.example.cafebackend.exception.*;
import com.example.cafebackend.mapper.AddOnMapper;
import com.example.cafebackend.mapper.MaterialMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.*;
import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.model.response.ForFindProdcut.*;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class ProductFormController {

    private ProductFormService productFormService;

    private ProductBaseService productBaseService;

    private ProductMapper productMapper;

    private AddOnMapper addOnMapper;

    private MaterialMapper materialMapper;

    //////////////////////////////////////////////////////////////////////////

    public MessageResponse createProductForm(String baseId, String prodFormTh, String prodFormEng, Double prodPrice,
            String description)
            throws BaseException {
        /// validate
        if (Objects.isNull(baseId) || baseId.isEmpty())
            throw ProductException.createFailRequestBaseNull();
        if (Objects.isNull(prodFormTh) || prodFormTh.isEmpty())
            throw ProductException.createFailRequestFormNull();
        if (Objects.isNull(prodFormEng) || prodFormEng.isEmpty())
            prodFormEng = "none";
        if (Objects.isNull(prodPrice))
            throw ProductException.createFailPriceRequestNull();
        if (Objects.isNull(description) || description.isEmpty())
            description = "none";
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if (productBase.isEmpty())
            throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check product form
        if (productFormService.checkExistsByFormTh(prodFormTh)) {
            for (ProductForm listForm : base.getProductForms())
                if (listForm.getProdFormTh().equals(prodFormTh))
                    throw ProductException.createFailFormDuplicate();
        }
        if (productFormService.checkExistsByFormEng(prodFormEng)) {
            for (ProductForm listForm : base.getProductForms())
                if (listForm.getProdFormTh().equals(prodFormEng))
                    throw ProductException.createFailFormDuplicate();
        }
        /// create product form
        ProductForm productForm = productFormService.createProductForm(base, prodFormTh, prodFormEng, prodPrice,
                description);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("add Product(f) success");
        res.setRes(productForm);
        return res;
    }

    public MessageResponse updateProductForm(Long formId, String prodFormTh, String prodFormEng, Double prodPrice,
            String description,
            Boolean isEnable) throws Exception {
        /// validate
        if (Objects.isNull(formId))
            throw ProductException.findFailRequestProductIdNull();
        /// verify
        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
        if (prodOpt.isEmpty())
            throw ProductException.findProductFail();
        ProductForm form = prodOpt.get();
        List<ProductForm> listForm = productFormService.findProductFormByBaseId(form.getProductBase().getProdBaseId());
        /// check product form
        if (!(Objects.isNull(prodFormTh) || prodFormTh.isEmpty())) {
            if (!prodFormTh.equals(form.getProdFormTh())) {
                for (ProductForm formInBase : listForm) {
                    if (formInBase.getProdFormTh().equals(prodFormTh))
                        throw ProductException.updateFailFormDuplicate();
                }
                form.setProdFormTh(prodFormTh);
            }
        }
        if (!(Objects.isNull(prodFormEng) || prodFormEng.isEmpty())) {
            if (!prodFormEng.equals(form.getProdFormEng())) {
                for (ProductForm formInBase : listForm) {
                    if (formInBase.getProdFormEng().equals(prodFormEng))
                        throw ProductException.updateFailFormDuplicate();
                }
                form.setProdFormEng(prodFormEng);
            }
        }
        /// check price
        if (!(Objects.isNull(prodPrice))) {
            if (!prodPrice.equals(form.getPrice())) {
                form.setPrice(prodPrice);
            }
        }

        /// check description
        if (!(Objects.isNull(description) || description.isEmpty())) {
            if (!description.equals(form.getDescription())) {
                form.setDescription(description);
            }
        }
        /// check isEnable
        if (!(Objects.isNull(isEnable))) {
            if (!isEnable.equals(form.getIsEnable())) {
                form.setIsEnable(isEnable);
            }
        }

        /// update product form
        ProductForm prod = productFormService.updateProductForm(form);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("update Product(f) success");
        res.setRes(prod);
        return res;
    }
    //////////////////////////////////////////////////////////////////////////

    private MessageResponse findProductFormByBaseId(String baseId, String addOn, String option,
            String haveMateUse, Integer pageSize, Integer pageNum) throws ProductException {
        Optional<ProductBase> baseOpt = productBaseService.findBaseById(baseId);
        if (baseOpt.isEmpty())
            throw ProductException.findBaseFail();
        // ProductBase productBase = baseOpt.get();
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        /// if addon true
        if (!(Objects.isNull(addOn) || addOn.isEmpty()) && addOn.equals("true")) {
            /// if option true
            if (!(Objects.isNull(option) || option.isEmpty()) && option.equals("true")) {
                List<ForProductFormAddOnOptionResponse> responses = new ArrayList<>();
                for (ProductForm form : productFormService.findProductFormByBaseIdPageable(baseId, pageable)) {
                    List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(form.getAddOn());
                    ForProductFormAddOnOptionResponse pf = productMapper.toForProductFormAddOnOptionResponse(form,
                            listAdd);
                    responses.add(pf);
                }
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get list Product(f) Addon Option By Base ID");
                res.setRes(responses);
                return res;
            }
            List<ForProductFormAddOnResponse> pfAddon = productMapper.toListForProductFormAddOnResponse(
                    productFormService.findProductFormByBaseIdPageable(baseId, pageable));
            /// res
            MessageResponse res = new MessageResponse();
            res.setMessage("get list Product(f) Addon By Base ID");
            res.setRes(pfAddon);
            return res;
        }
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get list Product(f) By Base ID");
        res.setRes(productFormService.findProductFormByBaseIdPageable(baseId, pageable));
        return res;
    }

    ////////////////////////////////////////////////////////////////

    public MessageResponse findProductForm(Long formId, String baseId, String addOn, String option,
            String haveMateUse, Integer pageSize, Integer pageNum) throws BaseException {
        /// verify
        ///////////////////////////
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 30;
        }
        /// if form id value
        if (!(Objects.isNull(formId))) {
            MessageResponse resById = findProductFormId(formId, addOn, option, haveMateUse);
            return resById;
        }
        /// if base id value
        if (!(Objects.isNull(baseId) || baseId.isEmpty())) {
            MessageResponse resByBaseId = findProductFormByBaseId(baseId, addOn, option, haveMateUse, pageSize,
                    pageNum);
            return resByBaseId;

        }
        /// res if all not value

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<ProductForm> productForm = productFormService.findProductFormAllPageable(pageable);
        List<ForProductFormInfoResponse> response = productMapper.toListForProductFormInfoResponse(productForm);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get list Product(f) All");
        res.setRes(response);
        return res;
    }

    ////////////////////////////////////////////////

    public MessageResponse deleteProductForm(Long prodId) throws BaseException {
        /// validate
        if (Objects.isNull(prodId))
            throw ProductException.findProductFail();
        Boolean product = productFormService.deleteFormById(prodId);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Product(f) success");
        res.setRes(product);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    private MessageResponse findProductFormId(Long formId, String addOn, String option,
            String haveMateUse) throws ProductException {
        Optional<ProductForm> productForm = productFormService.findProductFormById(formId);
        if (productForm.isEmpty())
            throw ProductException.findProductFail();
        ProductForm form = productForm.get();
        /// if addon true
        if (!(Objects.isNull(addOn) || addOn.isEmpty())) {
            if (addOn.equals("true")) {
                /// if option true
                if (!(Objects.isNull(option) || option.isEmpty())) {
                    if (option.equals("true")) {
                        List<ForAddOnResponse> listAdd = addOnMapper.toListForAddOnResponse(form.getAddOn());
                        ForProductFormInfoAddOnOptionResponse pf = productMapper
                                .toForProductFormInfoAddOnOptionResponse(form, listAdd);
                        /// res
                        MessageResponse res = new MessageResponse();
                        res.setMessage("get Product(f) Addon Option By Base ID");
                        res.setRes(pf);
                        return res;
                    }
                }

                /// if haveMateUse true
                if (!(Objects.isNull(haveMateUse) || haveMateUse.isEmpty())) {
                    if (haveMateUse.equals("true")) {
                        /// check res material use
                        List<MaterialUsedNec> ListMateNec = new ArrayList<>();
                        for (MaterialUsed mateUsed : form.getMaterialUsed()) {
                            MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed,
                                    mateUsed.getMaterial());
                            ListMateNec.add(materialUsedNec);
                        }
                        ForProductFormDetailResponse formDetail = new ForProductFormDetailResponse();
                        formDetail.setAddOn(form.getAddOn());
                        formDetail.setMaterialUsed(ListMateNec);
                        formDetail.setPrice(form.getPrice());
                        formDetail.setIsEnable(form.getIsEnable());
                        formDetail.setIsMaterialEnable(form.getIsMaterialEnable());
                        formDetail.setDescription(form.getDescription());
                        formDetail.setProdFormTh(form.getProdFormTh());
                        formDetail.setProdFormEng(form.getProdFormEng());
                        formDetail.setProdFormId(form.getProdFormId());
                        formDetail.setProductBase(form.getProductBase());
                        /// response
                        MessageResponse res = new MessageResponse();
                        res.setMessage("get materialUsed ");
                        res.setRes(formDetail);
                        return res;
                    }
                }
                ForProductFormInfoAddOnResponse pfAddon = productMapper.toForProductFormInfoAddOnResponse(form);
                /// res
                MessageResponse res = new MessageResponse();
                res.setMessage("get Product(f) Addon By Base ID");
                res.setRes(pfAddon);
                return res;
            }

        }
        /// if haveMateUse true
        if (!(Objects.isNull(haveMateUse) || haveMateUse.isEmpty())) {
            if (haveMateUse.equals("true")) {
                /// check res material use
                List<MaterialUsedNec> ListMateNec = new ArrayList<>();
                for (MaterialUsed mateUsed : form.getMaterialUsed()) {
                    MaterialUsedNec materialUsedNec = materialMapper.toMaterialUsedNec(mateUsed,
                            mateUsed.getMaterial());
                    ListMateNec.add(materialUsedNec);
                }
                ForProductFormDetailResponse formDetail = new ForProductFormDetailResponse();
                formDetail.setMaterialUsed(ListMateNec);
                formDetail.setPrice(form.getPrice());
                formDetail.setIsEnable(form.getIsEnable());
                formDetail.setIsMaterialEnable(form.getIsMaterialEnable());
                formDetail.setDescription(form.getDescription());
                formDetail.setProdFormTh(form.getProdFormTh());
                formDetail.setProdFormEng(form.getProdFormEng());
                formDetail.setProdFormId(form.getProdFormId());
                formDetail.setProductBase(form.getProductBase());
                /// response
                MessageResponse res = new MessageResponse();
                res.setMessage("get materialUsed ");
                res.setRes(formDetail);
                return res;
            }
        }
        ForProductFormInfoResponse response = productMapper.toForProductFormInfoResponse(productForm.get());
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Product(f) By ID");
        res.setRes(response);
        return res;
    }

}
