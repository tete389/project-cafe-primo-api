package com.example.cafebackend.controller;

import com.example.cafebackend.exception.*;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.*;
import com.example.cafebackend.model.response.ForFindProdcut.ForFindAddOnInPFResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForFindMateEnableAndCateInPFResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductFormResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@AllArgsConstructor
@Service
public class ProductFormController {

    private ProductFormService productFormService;

    private ProductBaseService productBaseService;

    private AddOnService addOnService;

    private ProductMapper productMapper;

    private FileService fileService;

    //public static String uploadDirectory = System.getProperty("user.dir");


    //////////////////////////////////////////////////////////////////////////

    public MessageResponse createProductForm(String baseId, String prodForm, String prodPrice, String bonusPoint, String description) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw ProductException.createFailRequestBaseNull();
        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.createFailRequestFormNull();
        if(Objects.isNull(prodPrice) || prodPrice.isEmpty()) throw ProductException.createFailPriceRequestNull();
        if(Objects.isNull(bonusPoint) || bonusPoint.isEmpty()) throw ProductException.createProductFail();
        if(Objects.isNull(description) || description.isEmpty()) throw ProductException.createProductFail();
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if(productBase.isEmpty()) throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check product form
        if(productFormService.checkExistsByForm(prodForm)) {
            for(ProductForm listForm : base.getProductForms())
                if (listForm.getProdForm().equals(prodForm)) throw ProductException.createFailFormDuplicate();
        }
        /// create product form
        Double setPrice = Double.valueOf(prodPrice);
        Double setBonusPoint = Double.valueOf(bonusPoint);
        ProductForm productForm = productFormService.createProductForm(base, prodForm, setPrice, setBonusPoint, description);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("add ProductForm success");
        res.setRes(productForm);
        return res;
    }

    public MessageResponse updateProductForm(String formId, String prodForm, String prodPrice, String bonusPoint, String description, String isEnable) throws Exception{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.findFailRequestFormNull();
        if(Objects.isNull(prodPrice) || prodPrice.isEmpty()) throw ProductException.findFailRequestPriceNull();
        if(Objects.isNull(bonusPoint) || bonusPoint.isEmpty()) throw ProductException.findFailRequestPointNull();
        if(Objects.isNull(description) || description.isEmpty()) throw ProductException.findProductFail();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw ProductException.findFailRequestEnableNull();
        /// verify
        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductForm form = prodOpt.get();
        /// check product form
        if(!prodForm.equals(form.getProdForm())) {
            for(ProductForm listForm : form.getProductBase().getProductForms())
                if (listForm.getProdForm().equals(prodForm)) throw ProductException.updateFailFormDuplicate();
            form.setProdForm(prodForm);
        }
        /// check price
        Double setPrice = Double.valueOf(prodPrice);
        if(!setPrice.equals(form.getPrice())) {
            form.setPrice(setPrice);
        }
        /// check bonus point
        Double setBonusPoint = Double.valueOf(bonusPoint);
        if(!setBonusPoint.equals(form.getBonusPoint())) {
            form.setPrice(setBonusPoint);
        }
        /// check description
        if(!description.equals(form.getDescription())) {
            form.setDescription(description);
        }
        /// check isEnable
        String enable = String.valueOf(form.getIsEnable());
        if(!isEnable.equals(enable)){
            form.setIsEnable(Boolean.valueOf(isEnable));
        }
        /// update product form
        ProductForm prod = productFormService.updateProductForm(form);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("update ProductForm success");
        res.setRes(prod);
        return res;
    }


//    public MessageResponse uploadImage(String formId, MultipartFile image) throws Exception {
//        /// validate
//        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(image == null) throw FileException.fileNull();
//        if(image.getSize() > 1048576 * 5 ) throw FileException.fileMaxSize();
//        String contentType = image.getContentType();
//        if (contentType == null) throw FileException.createFail();
//        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
//        if(!supportTypes.contains(contentType)) throw FileException.updateFailTypes();
//
//        /// upload to sever
//        //logger.info("HIT -/upload | File Name : {}", image.getOriginalFilename());
//        String filePath =  fileService.upload(image);
//        if(filePath == null) {
//            throw ProductException.createProductFail();
//        }
//
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm form = prodOpt.get();
//        form.setImage(filePath);
//        ProductForm productForm = productFormService.updateProductForm(form);
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update ProductForm success");
//        res.setRes(productForm);
//        return res;
//    }

//    public MessageResponse deleteImage(String formId) throws Exception {
//        /// validate
//        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm form = prodOpt.get();
//        form.setImage("none");
//        ProductForm productForm = productFormService.updateProductForm(form);
//        /// res
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update ProductForm success");
//        res.setRes(productForm);
//        return res;
//    }
//    //////////////////////////////////////////////////////////////////////////

    public MessageResponse updateAddOnInProductForm(String prodId, List<String> listAddOnId) throws Exception{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(listAddOnId)|| listAddOnId.isEmpty()) throw OptionException.addInfoFailRequestAddOnNull();
        /// verify
        Optional<ProductForm> product = productFormService.findProductFormById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        ProductForm productForm = product.get();
        /// check addOn
        List<AddOn> addOnList = new ArrayList<>();
        for (String addId : listAddOnId) {
            Optional<AddOn> addOn = addOnService.findAddOnById(addId);
            if(addOn.isEmpty()) throw OptionException.findFail();
            addOnList.add(addOn.get());
        }
        /// set addOn
        productForm.getAddOn().clear();
        productForm.getAddOn().addAll(addOnList);
        ProductForm prod = productFormService.updateProductForm(productForm);
        ForFindAddOnInPFResponse prodAddon = productMapper.toForFindAddOnInPFResponse(prod);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("add AddOn success");
        res.setRes(prodAddon);
        return res;
    }
    ////////////////////////////////////////////////

    public MessageResponse findProductFormById(String prodId) throws BaseException {
        /// validate
        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
        if(productForm.isEmpty()) throw ProductException.findProductFail();
        ForProductFormResponse response = productMapper.toForProductFormResponse(productForm.get());
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get ProductForm By ID");
        res.setRes(response);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse findProductFormAll() {
        /// verify
        List<ProductForm> productForm =  productFormService.findListProduct();
        List<ForProductFormResponse> response = productMapper.toListForProductFormResponse(productForm);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get ProductForm All");
        res.setRes(response);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse findProductInFoById(String prodId) throws BaseException {
        /// validate
        Optional<ProductForm> productForm =  productFormService.findProductFormById(prodId);
        if(productForm.isEmpty()) throw ProductException.findProductFail();
        /// get res
        ForFindMateEnableAndCateInPFResponse prodRes = productMapper.toForFindMateEnableAndCateInPFResponse(productForm.get());
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Product info By ID");
        res.setRes(prodRes);
        return res;
    }
    ////////////////////////////////////////////////////////////////


    public MessageResponse findProductInFoAll() {
        /// verify
        List<ProductForm> productForm =  productFormService.findListProduct();
        List<ForFindMateEnableAndCateInPFResponse> pdList = productMapper.toListForFindMateEnableAndCateInPFResponse(productForm);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Product info All");
        res.setRes(pdList);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse findAddOnInProductFormId(String prodId) throws BaseException {
        /// validate
        Optional<ProductForm> p =  productFormService.findProductFormById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForFindAddOnInPFResponse prodRes = productMapper.toForFindAddOnInPFResponse(p.get());
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get AddOn In ProductForm");
        res.setRes(prodRes);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse deleteProductForm(String prodId) throws BaseException {
        Boolean product =  productFormService.deleteFormById(prodId);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("delete product success");
        res.setRes(product);
        return res;
    }
    ////////////////////////////////////////////////////////////////

}
