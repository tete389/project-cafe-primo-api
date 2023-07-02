package com.example.cafebackend.api;

import com.example.cafebackend.controller.CategoryController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.model.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryApi {

    private final CategoryController categoryController;

    public CategoryApi(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/createCategory")
    public ResponseEntity<MessageResponse> createCategory(@RequestParam("cateName") String cateName) throws BaseException {
        MessageResponse res = categoryController.createCategory(cateName);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateCategory")
    public ResponseEntity<MessageResponse> updateCategory(@RequestParam("cateId") String cateId,
                                                          @RequestParam("cateName") String name,
                                                          @RequestParam("isEnable") String isEnable,
                                                          @RequestParam("isRecommend") String isRecommend) throws BaseException {
        MessageResponse res = categoryController.updateCategory(cateId, name, isEnable, isRecommend);
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/updateProductFormInToCategory")
//    public ResponseEntity<MessageResponse> updateProductFormInToCategory(@RequestParam("cateId") String cateId,
//                                                                         @RequestParam("listProdFormId") List<String> prodFormId) throws BaseException {
//        MessageResponse res = categoryController.updateProductFormInCategory(cateId, prodFormId);
//        return ResponseEntity.ok(res);
//    }

    @PostMapping("/updateProductBaseIntoCategory")
    public ResponseEntity<MessageResponse> updateProductBaseInToCategory(@RequestParam("cateId") String cateId,
                                                                         @RequestParam("listProdBaseId") List<String> prodBaseId) throws BaseException {
        MessageResponse res = categoryController.updateProductBaseInCategory(cateId, prodBaseId);
        return ResponseEntity.ok(res);
    }
    ////////////////////////

    @PostMapping("/getCategoryById")
    public ResponseEntity<MessageResponse> getCategoryById(@RequestParam("cateId") String cateId) throws BaseException {
        MessageResponse res = categoryController.findCategoryById(cateId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getCategoryAll")
    public ResponseEntity<MessageResponse> getCategoryAll() throws BaseException {
        MessageResponse res = categoryController.findCategoryAll();
        return ResponseEntity.ok(res);
    }
    ////////////////////////

//    @PostMapping("/getProductFormByCategoryId")
//    public ResponseEntity<MessageResponse> findProductFormByCategoryId(@RequestParam("cateId") String cateId) throws BaseException {
//        MessageResponse res = categoryController.findListProductFormByCategoryId(cateId);
//        return ResponseEntity.ok(res);
//    }
//
//    @GetMapping("/getProductFormByCategoryAll")
//    public ResponseEntity<MessageResponse> findProductFormByCategoryAll() throws BaseException {
//        MessageResponse res = categoryController.findListProductFormByCategoryAll();
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getProductFormInFoByCategoryId")
//    public ResponseEntity<MessageResponse> findProductFormInFoByCategoryId(@RequestParam("cateId") String cateId) throws BaseException {
//        MessageResponse res = categoryController.findListProductFormInfoByCategoryId(cateId);
//        return ResponseEntity.ok(res);
//    }
    ////////////////////////

    @GetMapping("/getProductBaseByCategoryId")
    public ResponseEntity<MessageResponse> findProductBaseByCategoryId(@RequestParam("cateId") String cateId) throws BaseException {
        MessageResponse res = categoryController.findListProductBaseByCategoryId(cateId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getProductBaseByCategoryAll")
    public ResponseEntity<MessageResponse> findProductBaseByCategoryAll() throws BaseException {
        MessageResponse res = categoryController.findListProductBaseByCategoryAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProductBaseInFoByCategoryId")
    public ResponseEntity<MessageResponse> findProductBaseInFoByCategoryId(@RequestParam("cateId") String cateId) throws BaseException {
        MessageResponse res = categoryController.findListProductBaseInfoByCategoryId(cateId);
        return ResponseEntity.ok(res);
    }
    ////////////////////////

    @PostMapping("/deleteCategory")
    public ResponseEntity<MessageResponse> deleteCategory(@RequestParam("cateId") String cateId) throws BaseException {
        MessageResponse res = categoryController.deleteCategory(cateId);
        return ResponseEntity.ok(res);
    }

}
