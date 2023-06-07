package com.example.cafebackend.api;

import com.example.cafebackend.controller.BaseProductController;
import com.example.cafebackend.controller.ProductController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.table.BaseProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/product")
public class ProductApi {

    private final ProductController productController;
    private final BaseProductController baseProductController;

    public ProductApi(ProductController productController, BaseProductController baseProductController) {
        this.productController = productController;
        this.baseProductController = baseProductController;
    }

    ///////////////////////////////////////////////////////////

    ////////  base product /////////////////////
    @PostMapping("/createBase")
    public ResponseEntity<MessageResponse> createBaseProduct(@RequestParam("prodTitle") String prodTitle,
                                                             @RequestParam("description") String description) throws BaseException {
        MessageResponse res = baseProductController.createBaseProduct(prodTitle, description);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getListProductByBaseId")
    public ResponseEntity<MessageResponse> findListProductByProductId(@RequestParam("baseId") String baseId) throws BaseException {
        MessageResponse res = productController.findListProductByBaseId(baseId);
        return ResponseEntity.ok(res);
    }
    ////////////////////////////


    ////////  Product /////////////////////

    @PostMapping("/createProduct")
    public ResponseEntity<MessageResponse> createProduct(@RequestParam("baseId") String baseId,
                                                         @RequestParam("prodForm") String prodForm,
                                                         @RequestParam("prodPrice") Double prodPrice) throws BaseException {
        if(prodPrice == null) prodPrice = 0.0;
        MessageResponse res = productController.createProduct(baseId, prodForm, prodPrice);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/setFormProduct")
    public ResponseEntity<MessageResponse> setFormProduct(@RequestParam("prodId") String prodId,
                                                          @RequestParam("prodForm") String prodForm) throws BaseException {
        MessageResponse res = productController.setFormProduct(prodId, prodForm);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/setDescription")
    public ResponseEntity<MessageResponse> setDescriptionProduct(@RequestParam("prodId") String prodId,
                                                                 @RequestParam("description") String description) throws BaseException {
        MessageResponse res = productController.setDescriptionProduct(prodId, description);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/setBonusPoint")
    public ResponseEntity<MessageResponse> setBonusPointProdDetail(@RequestParam("prodId") String prodId,
                                                                   @RequestParam("bonusPoint") Double point) throws BaseException {
        MessageResponse res = productController.setBonusPointProduct(prodId, point);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/setForSale")
    public ResponseEntity<MessageResponse> setForSale(@RequestParam("prodId") String prodId,
                                                      @RequestParam("forSale") Boolean forSale) throws BaseException {
        MessageResponse res = productController.setForSaleProduct(prodId, forSale);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/setEnable")
    public ResponseEntity<MessageResponse> setEnable(@RequestParam("prodId") String prodId,
                                                     @RequestParam("enable") Boolean enable) throws BaseException {
        MessageResponse res = productController.setEnableProduct(prodId, enable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/setPrice")
    public ResponseEntity<MessageResponse> setPriceProd(@RequestParam("prodId") String prodId,
                                                        @RequestParam("price") Double price) throws BaseException {
        MessageResponse res = productController.setPriceProduct(prodId, price);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/upLoadImage")
    public ResponseEntity<MessageResponse> upLoadImageProd(@RequestParam("prodId") String prodId,
                                                           @RequestParam("file") MultipartFile image) throws BaseException {
        MessageResponse res = productController.uploadImageProduct(prodId, image);
        return ResponseEntity.ok(res);
    }
    ////////////////////////////

    ////////////////////////////////////  add info ** to product
    @PostMapping("/addInfoCategory")
    public ResponseEntity<MessageResponse> addInfoCategory(@RequestParam("prodId") String prodId,
                                                           @RequestParam("listCateId") List<String> listCateId) throws BaseException {
        MessageResponse res = productController.addInfoCategory(prodId, listCateId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addInfoAddOn")
    public ResponseEntity<MessageResponse> addInfoAddOn(@RequestParam("prodId") String prodId,
                                                        @RequestParam("listAddOnId") List<String> listAddOnId) throws BaseException {
        MessageResponse res = productController.addInfoAddOn(prodId, listAddOnId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addInfoIngredient")
    public ResponseEntity<MessageResponse> addInfoIngredientAndMaterial(@RequestParam("prodId") String prodId,
                                                                        @RequestParam("mateId") String mateId,
                                                                        @RequestParam("mateCount") Double mateCount,
                                                                        @RequestParam("mateUnit") String mateUnit,
                                                                        @RequestParam("description") String description) throws BaseException {
        MessageResponse res = productController.addInfoIngredientAndMaterial(prodId, mateId, mateCount, mateUnit, description);
        return ResponseEntity.ok(res);
    }
    ////////////////////////////

    ////////////////////////////////////  delete info ** from product
//    @PostMapping("/delInfoCategory")
//    public ResponseEntity<MessageResponse> delInfoCategory(@RequestParam("prodId") String prodId,
//                                                           @RequestParam("listCateId") List<String> listCateId) throws BaseException {
//        MessageResponse res = productController.delInfoCategory(prodId, listCateId);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/delInfoAddOn")
//    public ResponseEntity<MessageResponse> delInfoAddOn(@RequestParam("prodId") String prodId,
//                                                         @RequestParam("listAddOnId") List<String> listAddOnId) throws BaseException {
//        MessageResponse res = productController.delInfoAddOn(prodId, listAddOnId);
//        return ResponseEntity.ok(res);
//    }

    @PostMapping("/delInfoIngredient")
    public ResponseEntity<MessageResponse> delInfoIngredientAndMaterial(@RequestParam("prodId") String prodId,
                                                                        @RequestParam("listMateId") List<String> listMateId) throws BaseException {
        MessageResponse res = productController.delInfoIngredientAndMaterial(prodId, listMateId);
        return ResponseEntity.ok(res);
    }
    ////////////////////////////

    ////////////////////////////////////  find info ** from product

    //////////////////// find product and list **
    @GetMapping("/getListProductInfo")
    public ResponseEntity<MessageResponse> findListProductInfo() throws BaseException {
        MessageResponse res = productController.findListProductInfo();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getListOptionByProductId")
    public ResponseEntity<MessageResponse> findProductAndListOptionById(@RequestParam("prodId") String prodId) throws BaseException {
        MessageResponse res = productController.findProductAndListOptionById(prodId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getListIngredientByProductId")
    public ResponseEntity<MessageResponse> findProductAndListIngredientById(@RequestParam("prodId") String prodId) throws BaseException {
        MessageResponse res = productController.findProductAndListIngredientById(prodId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getListCategoryByProductId")
    public ResponseEntity<MessageResponse> findProductAndListCategoryById(@RequestParam("prodId") String prodId) throws BaseException {
        MessageResponse res = productController.findProductAndListCategoryById(prodId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProductById")
    public ResponseEntity<MessageResponse> findProductById(@RequestParam("prodId") String prodId) throws BaseException {
        MessageResponse res = productController.findProductById(prodId);
        return ResponseEntity.ok(res);
    }
    ////////////////////////////

    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> deleteProductById(@RequestParam("prodId") String prodId) throws BaseException {
        MessageResponse res = productController.deleteProduct(prodId);
        return ResponseEntity.ok(res);
    }











//    @PostMapping("/createProdHaveImage")
//    public ResponseEntity<MessageResponse> createProdHaveType(@RequestParam("file") MultipartFile image,
//                                                              @RequestParam("prodName") String prodName,
//                                                              @RequestParam("prodPrice") String prodPrice,
//                                                              @RequestParam("prodType") String prodType,
//                                                              @RequestParam("prodSweet") String prodSweet,
//                                                              @RequestParam("prodCate") List<String> prodCate
//
//    ) throws BaseException {
//        MessageResponse res = productDetailController.createProdHaveImage(image, prodName, prodPrice, prodType, prodSweet, prodCate);
//        return ResponseEntity.ok(res);
//    }
//
//
//    @PostMapping("/createProdImageLess")
//    public ResponseEntity<MessageResponse> createProdImageLess(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.createProdImageLess(request);
//        return ResponseEntity.ok(res);
//    }
//
//
//    @PostMapping("/getProdById")
//    public ResponseEntity<MessageResponse> getProdById(@RequestBody ProductDetail request) throws BaseException {
//        MessageResponse res = productDetailController.getProdById(request);
//        return ResponseEntity.ok(res);
//    }

//    @PostMapping("/updateProd")
//    public ResponseEntity<MessageResponse> updateProdById(@RequestBody Product request) throws BaseException {
//        MessageResponse res = productController.updateProd(request);
//        return ResponseEntity.ok(res);
//    }



//    @PostMapping("/updateProd")
//    public ResponseEntity<MessageResponse> updateProdById(@RequestParam("file") MultipartFile image,
//                                                          @RequestPart String prodName,
//                                                          @RequestPart String prodPrice,
//                                                          @RequestPart String prodType,
//                                                          @RequestPart String prodSweet,
//                                                          @RequestPart List<String> prodCate
//    ) throws BaseException {
//        MessageResponse res = productController.updateProd(image, prodName, prodPrice, prodType, prodSweet, prodCate);
//        return ResponseEntity.ok(res);
//    }









//    @PostMapping("/uploadImageProd")
//    public ResponseEntity<MessageResponse> uploadImageProd(@RequestParam("file") MultipartFile image,
//                                                           @RequestParam("prodId") String prodId
//    ) throws BaseException {
//        MessageResponse res = productDetailController.upLoadImageProd(image, prodId);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updateStatusProd")
//    public ResponseEntity<MessageResponse> updateStatusProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.updateStatusProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updatePriceProd")
//    public ResponseEntity<MessageResponse> updatePriceProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.updatePriceProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updateCateInProd")
//    public ResponseEntity<MessageResponse> updateCateInProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.updateCateInProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updateSweetProd")
//    public ResponseEntity<MessageResponse> updateSweetProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.updateSweetProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updateAddInProd")
//    public ResponseEntity<MessageResponse> updateAddInProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.updateAddInProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updateMateProd")
//    public ResponseEntity<MessageResponse> updateMateInProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productDetailController.updateMateInProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/deleteProd")
//    public ResponseEntity<MessageResponse> deleteProd(@RequestBody ProductDetail request) throws BaseException {
//        MessageResponse res = productDetailController.deleteProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getProdByCateId")
//    public ResponseEntity<MessageResponse> getProdByCateId(@RequestBody Category request) {
//        MessageResponse res = productDetailController.getProdByCateId(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getProdByMateId")
//    public ResponseEntity<MessageResponse> getProdByMateId(@RequestBody Material request) {
//        MessageResponse res = productDetailController.getProdByMateId(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getProdByTypeId")
//    public ResponseEntity<MessageResponse> getProdByTypeId(@RequestBody Type request) {
//        MessageResponse res = productDetailController.getProdByTypeId(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getAllProdByTypeId")
//    public ResponseEntity<MessageResponse> getAllProdByTypeId(@RequestBody Type request) {
//        MessageResponse res = productDetailController.getAllProdByType(request.getTypeId());
//        return ResponseEntity.ok(res);
//    }
//
//    @GetMapping("/getProdForCustomer")
//    public ResponseEntity<MessageResponse> getProdForCustomer() {
//        MessageResponse res = productDetailController.getProdForCustomer();
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getProdByIdToSell")
//    public ResponseEntity<MessageResponse> getProdByIdToSell(@RequestBody ProductDetail request) throws BaseException {
//        MessageResponse res = productDetailController.getProdByIdToSell(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @GetMapping("/getAllProdForEmployee")
//    public ResponseEntity<MessageResponse> getAllProd() {
//        MessageResponse res = productDetailController.getAllProd();
//        return ResponseEntity.ok(res);
//    }
//
//    @GetMapping("/getAllProdToSearch")
//    public ResponseEntity<MessageResponse> getAllProdToSearch() throws BaseException {
//        MessageResponse res = productDetailController.getProdToSearch();
//        return ResponseEntity.ok(res);
//    }
//
//    @GetMapping("/getStartData")
//    public ResponseEntity<MessageResponse> getProdCountInCate() throws BaseException {
//        MessageResponse res = productDetailController.getStartData();
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getVerifyName")
//    public ResponseEntity<MessageResponse> verifyName(@RequestBody ProductDetail request) throws BaseException {
//        MessageResponse res = productDetailController.getVerifyNameProd(request);
//        return ResponseEntity.ok(res);
//    }
















//    @PostMapping("/addCateInProd")
//    public ResponseEntity<MessageResponse> addCateInProd(@RequestBody ProdCateRequest request) throws BaseException {
//        MessageResponse res = productController.addCateInToProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/addMateInProd")
//    public ResponseEntity<MessageResponse> addMateInProd(@RequestBody ProdMateRequest request) throws BaseException {
//        MessageResponse res = productController.addMateInToProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/addTypeInProd")
//    public ResponseEntity<MessageResponse> addTypeInProd(@RequestBody ProdRequest request) throws BaseException {
//        MessageResponse res = productController.addTypeInToProd(request);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/addAddInProd")
//    public ResponseEntity<MessageResponse> addAddInProd(@RequestBody ProdAddRequest request) throws BaseException {
//        MessageResponse res = productController.addAddInToProd(request);
//        return ResponseEntity.ok(res);
//    }


}
