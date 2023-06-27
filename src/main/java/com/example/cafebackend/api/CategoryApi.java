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

    @PostMapping("/updateProductInCategory")
    public ResponseEntity<MessageResponse> updateProductInCategory(@RequestParam("cateId") String cateId,
                                                                   @RequestParam("listProdFormId") List<String> prodFormId) throws BaseException {
        MessageResponse res = categoryController.updateProductInCategory(cateId, prodFormId);
        return ResponseEntity.ok(res);
    }

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

    @PostMapping("/getProductInCategoryById")
    public ResponseEntity<MessageResponse> findProductInCategoryById(@RequestParam("cateId") String cateId) throws BaseException {
        MessageResponse res = categoryController.findListProductByCategoryId(cateId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getProductInCategoryAll")
    public ResponseEntity<MessageResponse> findProductInCategoryAll() throws BaseException {
        MessageResponse res = categoryController.findListProductByCategoryAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteCategory")
    public ResponseEntity<MessageResponse> deleteCategory(@RequestParam("cateId") String cateId) throws BaseException {
        MessageResponse res = categoryController.deleteCategory(cateId);
        return ResponseEntity.ok(res);
    }

}
