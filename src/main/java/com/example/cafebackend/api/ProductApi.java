package com.example.cafebackend.api;

import com.example.cafebackend.controller.ProductBaseController;
import com.example.cafebackend.controller.ProductFormController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.response.ForFindCategory.ForCategoryResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductFormResponse;
import com.example.cafebackend.model.request.ProductBaseInsertToCategoryRequest;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin()
@RestController
@RequestMapping("/product")
public class ProductApi {

    private final ProductFormController productFormController;
    private final ProductBaseController productBaseController;

    public ProductApi(ProductFormController productFormController, ProductBaseController productBaseController) {
        this.productFormController = productFormController;
        this.productBaseController = productBaseController;

    }

    ///////////////////////////////////////////////////////////////////////////////////////

    //////// product base /////////////////////
    @PostMapping("/createProductBase")
    public ResponseEntity<MessageResponse> createBaseProduct(@RequestBody ProductBase request) throws BaseException {
        MessageResponse res = productBaseController.createProductBase(request.getProdTitleTh(),
                request.getProdTitleEng());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateProductBase")
    public ResponseEntity<MessageResponse> updateBase(@RequestBody ProductBase request) throws BaseException {
        MessageResponse res = productBaseController.updateProductBase(request.getProdBaseId(), request.getProdTitleTh(),
                request.getProdTitleEng(),
                request.getDescription(), request.getIsEnable());
        return ResponseEntity.ok(res);
    }

    ////////////////////////
    @PutMapping("/updateImportBase")
    public ResponseEntity<MessageResponse> updateProductBaseInToCategory(@RequestBody ForCategoryResponse request)
            throws BaseException {
        MessageResponse res = productBaseController.updateProductBaseIntoCategory(request.getCateId(),
                request.getProductBase());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateRemoveBase")
    public ResponseEntity<MessageResponse> deleteProductBaseFromCategory(@RequestBody ForCategoryResponse request)
            throws BaseException {
        MessageResponse res = productBaseController.deleteProductBaseFromCategory(request.getCateId(),
                request.getProductBase());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateIntoCategory")
    public ResponseEntity<MessageResponse> insertProductBaseInToCategory(
            @RequestBody ProductBaseInsertToCategoryRequest request) throws BaseException {
        MessageResponse res = productBaseController.updateCategoryIntoProductBase(request.getProdBaseId(),
                request.getListCategory());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/deleteFormCategory")
    public ResponseEntity<MessageResponse> deleteProductBaseInToCategory(
            @RequestBody ProductBaseInsertToCategoryRequest request)
            throws BaseException {
        MessageResponse res = productBaseController.deleteCategoryFromProductBase(request.getProdBaseId(),
                request.getListCategory());
        return ResponseEntity.ok(res);
    }
    ////////////////////////

    @PutMapping("/uploadImage")
    public ResponseEntity<MessageResponse> upLoadImageProd(@RequestParam("baseId") String formId,
            @RequestParam("file") MultipartFile image) throws Exception {
        MessageResponse res = productBaseController.uploadImage(formId, image);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/deleteImage")
    public ResponseEntity<MessageResponse> deleteImageProd(@RequestBody ProductBase request) throws Exception {
        MessageResponse res = productBaseController.deleteImage(request.getProdBaseId());
        return ResponseEntity.ok(res);
    }
    ////////////////////////////////////////

    @GetMapping("/getProductBase")
    public ResponseEntity<MessageResponse> findBaseById(@RequestParam(name = "baseId", required = false) String baseId,
            @RequestParam(name = "cateId", required = false) String cateId,
            @RequestParam(name = "minPrice", required = false) String minPrice,
            @RequestParam(name = "haveform", required = false) String haveForm,
            @RequestParam(name = "haveCountform", required = false) String haveCountFrom,
            @RequestParam(name = "haveMateUse", required = false) String haveMateUse,
            @RequestParam(name = "haveCate", required = false) String haveCate,
            @RequestParam(name = "pageSize", required = false) Integer PageSize,
            @RequestParam(name = "pageNum", required = false) Integer PageNum) throws BaseException {
        MessageResponse res = productBaseController.findProductBase(baseId, cateId, minPrice, haveForm,haveCountFrom, haveMateUse,
                haveCate, PageSize, PageNum);
        return ResponseEntity.ok(res);
    }
    ////////////////////

    @PostMapping("/deleteProductBase")
    public ResponseEntity<MessageResponse> deleteBase(@RequestBody ProductBase request) throws BaseException {
        MessageResponse res = productBaseController.deleteProductBase(request.getProdBaseId());
        return ResponseEntity.ok(res);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////// product form /////////////////////

    @PostMapping("/createProductForm")
    public ResponseEntity<MessageResponse> createForm(@RequestBody ForProductFormResponse request)
            throws BaseException {
        MessageResponse res = productFormController.createProductForm(request.getProductBaseId(),
                request.getProdFormTh(), request.getProdFormEng(),
                request.getPrice(), request.getDescription());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateProductForm")
    public ResponseEntity<MessageResponse> updateForm(@RequestBody ProductForm request) throws Exception {
        MessageResponse res = productFormController.updateProductForm(request.getProdFormId(), request.getProdFormTh(),
                request.getProdFormEng(),
                request.getPrice(), request.getDescription(), request.getIsEnable());
        return ResponseEntity.ok(res);
    }

    ////////////////////////////////////////

    @GetMapping("/getProductForm")
    public ResponseEntity<MessageResponse> findListFormByBaseId(
            @RequestParam(name = "formId", required = false) Long formId,
            @RequestParam(name = "baseId", required = false) String baseId,
            @RequestParam(name = "addOn", required = false) String addOn,
            @RequestParam(name = "option", required = false) String option,
            @RequestParam(name = "haveMateUse", required = false) String haveMateUse,
            @RequestParam(name = "pageSize", required = false) Integer PageSize,
            @RequestParam(name = "pageNum", required = false) Integer PageNum) throws BaseException {
        MessageResponse res = productFormController.findProductForm(formId, baseId, addOn, option, haveMateUse,
                PageSize, PageNum);
        return ResponseEntity.ok(res);
    }

    ////////////////////////////////////////

    @PostMapping("/deleteProductForm")
    public ResponseEntity<MessageResponse> deleteForm(@RequestBody ProductForm request) throws BaseException {
        MessageResponse res = productFormController.deleteProductForm(request.getProdFormId());
        return ResponseEntity.ok(res);
    }

}
