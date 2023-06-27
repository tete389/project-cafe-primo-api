package com.example.cafebackend.api;

import com.example.cafebackend.controller.ProductBaseController;
import com.example.cafebackend.controller.ProductFormController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.request.MateUsedRequest;
import com.example.cafebackend.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    ////////   product base /////////////////////
    @PostMapping("/createProductBase")
    public ResponseEntity<MessageResponse> createBaseProduct(@RequestParam("prodTitle") String prodTitle) throws BaseException {
        MessageResponse res = productBaseController.createProductBase(prodTitle);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateProductBase")
    public ResponseEntity<MessageResponse> updateBase(@RequestParam("baseId") String baseId,
                                                      @RequestParam("prodTitle") String prodTitle,
                                                      @RequestParam("description") String des,
                                                      @RequestParam("isEnable") String isEnable
                                                      ) throws BaseException {
        MessageResponse res = productBaseController.updateProductBase(baseId, prodTitle, des, isEnable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProductBaseById")
    public ResponseEntity<MessageResponse> findListBaseById(@RequestParam("baseId") String baseId) throws BaseException {
        MessageResponse res = productBaseController.findBaseById(baseId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getProductBaseAll")
    public ResponseEntity<MessageResponse> findListBaseAll() throws BaseException {
        MessageResponse res = productBaseController.findBaseAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProductFormInBaseId")
    public ResponseEntity<MessageResponse> findListFormByBaseId(@RequestParam("baseId") String baseId) throws BaseException {
        MessageResponse res = productBaseController.findFormInBaseId(baseId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteProductBase")
    public ResponseEntity<MessageResponse> deleteBase(@RequestParam("baseId") String baseId) throws BaseException {
        MessageResponse res = productBaseController.deleteProductBase(baseId);
        return ResponseEntity.ok(res);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////  product form /////////////////////

    @PostMapping("/createProductForm")
    public ResponseEntity<MessageResponse> createForm(@RequestParam("baseId") String baseId,
                                                      @RequestParam("prodForm") String prodForm,
                                                      @RequestParam("prodPrice") String prodPrice,
                                                      @RequestParam("bonusPoint") String bonusPoint,
                                                      @RequestParam("description") String description) throws BaseException {
        MessageResponse res = productFormController.createProductForm(baseId, prodForm, prodPrice, bonusPoint, description);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateProductForm")
    public ResponseEntity<MessageResponse> updateForm(@RequestParam("formId") String formId,
                                                      @RequestParam("prodForm") String prodForm,
                                                      @RequestParam("prodPrice") String prodPrice,
                                                      @RequestParam("bonusPoint") String bonusPoint,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("isEnable") String isEnable) throws Exception {
        MessageResponse res = productFormController.updateProductForm(formId, prodForm, prodPrice, bonusPoint, description, isEnable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<MessageResponse> upLoadImageProd(@RequestParam("formId") String formId,
                                                           @RequestParam("file") MultipartFile image) throws Exception {
        MessageResponse res = productFormController.uploadImage(formId, image);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteImage")
    public ResponseEntity<MessageResponse> deleteImageProd(@RequestParam("formId") String formId) throws Exception {
        MessageResponse res = productFormController.deleteImage(formId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateAddOnInProductForm")
    public ResponseEntity<MessageResponse> updateAddOnInProductForm(@RequestParam("formId") String formId,
                                                                    @RequestParam("listAddId") List<String> listAddId) throws Exception {
        MessageResponse res = productFormController.updateAddOnInProductForm(formId, listAddId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProductFormById")
    public ResponseEntity<MessageResponse> findFormById(@RequestParam("formId") String formId) throws BaseException {
        MessageResponse res = productFormController.findProductFormById(formId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getProductFormAll")
    public ResponseEntity<MessageResponse> findListProductAll() {
        MessageResponse res = productFormController.findProductFormAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProductInFoById")
    public ResponseEntity<MessageResponse> findProductInfoById(@RequestParam("formId") String formId) throws BaseException {
        MessageResponse res = productFormController.findProductInFoById(formId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getProductInFoAll")
    public ResponseEntity<MessageResponse> findProductInfoAll() {
        MessageResponse res = productFormController.findProductInFoAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAddOnInProductFormId")
    public ResponseEntity<MessageResponse> getAddOnInProductFormId(@RequestParam("formId") String formId) throws BaseException {
        MessageResponse res = productFormController.findAddOnInProductFormId(formId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteProductForm")
    public ResponseEntity<MessageResponse> deleteForm(@RequestParam("formId") String formId) throws BaseException {
        MessageResponse res = productFormController.deleteProductForm(formId);
        return ResponseEntity.ok(res);
    }

}
