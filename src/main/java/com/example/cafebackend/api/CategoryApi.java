package com.example.cafebackend.api;

import com.example.cafebackend.controller.CategoryController;
import com.example.cafebackend.model.request.ProdCateRequest;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cate")
public class CategoryApi {

    private final CategoryController categoryController;

    public CategoryApi(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/createCate")
    public ResponseEntity<String> createCate(@RequestBody Category request) {
        String res = categoryController.createCategory(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAllCate")
    public ResponseEntity<List<Category>> getAllCate() {
        List<Category> res = categoryController.getAllCate();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getCateById")
    public ResponseEntity<Category> getCateByCateId(@RequestBody Category request) {
        Category res = categoryController.getCateById(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateCate")
    public ResponseEntity<String> updateCate(@RequestBody Category request) {
        String res = categoryController.updateCate(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteCate")
    public ResponseEntity<String> deleteCate(@RequestBody Category request) {
        String res = categoryController.deleteCate(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addProdInCate")
    public ResponseEntity<String> addProdInCate(@RequestBody ProdCateRequest request) {
        String res = categoryController.addProductInCate(request);
        return ResponseEntity.ok(res);
    }
}
