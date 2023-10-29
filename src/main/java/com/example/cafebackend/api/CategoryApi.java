package com.example.cafebackend.api;

import com.example.cafebackend.controller.CategoryController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.model.response.MessageResponse;

import com.example.cafebackend.table.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryApi {

    private final CategoryController categoryController;

    public CategoryApi(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/createCategory")
    public ResponseEntity<MessageResponse> createCategory(@RequestBody Category request) throws BaseException {
        MessageResponse res = categoryController.createCategory(request.getCateNameTh(), request.getCateNameEng());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<MessageResponse> updateCategory(@RequestBody Category request) throws BaseException {
        MessageResponse res = categoryController.updateCategory(request.getCateId(), request.getCateNameTh(),
                request.getCateNameEng(), request.getIsEnable(), request.getIsRecommend());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getCategory")
    public ResponseEntity<MessageResponse> getCategoryById(
            @RequestParam(name = "cateId", required = false) String cateId,
            @RequestParam(name = "base", required = false) String base,
            @RequestParam(name = "minPrice", required = false) String minPrice,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNum", required = false) Integer pageNum) throws BaseException {
        MessageResponse res = categoryController.findCategory(cateId, base, minPrice, pageSize, pageNum);
        return ResponseEntity.ok(res);
    }

    ////////////////////////

    @PostMapping("/deleteCategory")
    public ResponseEntity<MessageResponse> deleteCategory(@RequestBody Category request) throws BaseException {
        MessageResponse res = categoryController.deleteCategory(request.getCateId());
        return ResponseEntity.ok(res);
    }

}
