package com.example.cafebackend.api;

import com.example.cafebackend.controller.ProductController;
import com.example.cafebackend.model.request.*;
import com.example.cafebackend.model.response.ProductCustomerResponse;
import com.example.cafebackend.model.response.ProductResponse;
import com.example.cafebackend.repository.ProductRepository;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Product;
import com.example.cafebackend.table.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prod")
public class ProductApi {

    private final ProductController productController;

    public ProductApi(ProductController productController) {
        this.productController = productController;
    }

    ///////////////////////////////////////////////////////////

    @PostMapping("/createProd")
    public ResponseEntity<String> createProd(@RequestBody ProdRequest request) {
        String res = productController.createProduct(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProdById")
    public ResponseEntity<Product> getProdById(@RequestBody Product request) {
        Product res = productController.getProdById(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateProd")
    public ResponseEntity<String> updateProdById(@RequestBody Product request) {
        String res = productController.updateProduct(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteProd")
    public ResponseEntity<String> deleteProd(@RequestBody Product request) {
        String res = productController.deleteProd(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProdByCateId")
    public ResponseEntity<List<ProductResponse>> getProdByCateId(@RequestBody Category request) {
        List<ProductResponse> res = productController.getProdByCateId(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getProdByMateId")
    public ResponseEntity<List<ProductResponse>> getProdByMateId(@RequestBody Material request) {
        List<ProductResponse> res = productController.getProdByMateId(request);
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/getProdByTypeId")
//    public ResponseEntity<List<ProductResponse>> getProdByTypeId(@RequestBody Type request) {
//        List<ProductResponse> res = productController.getProdByTypeId(request);
//        return ResponseEntity.ok(res);
//    }

    @PostMapping("/getProdForCustomer")
    public ResponseEntity<List<ProductCustomerResponse>> getProdForCustomer() {
        List<ProductCustomerResponse> res = productController.getProdForCustomer();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAllProdForEmployee")
    public ResponseEntity<List<Product>> getAllProd() {
        List<Product> res = productController.getAllProd();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addCateInProd")
    public ResponseEntity<String> addCateInProd(@RequestBody ProdCateRequest request) {
        String res = productController.addCategoryInProd(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addMateInProd")
    public ResponseEntity<String> addMateInProd(@RequestBody ProdMateRequest request) {
        String res = productController.addMaterialInProd(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addTypeInProd")
    public ResponseEntity<String> addTypeInProd(@RequestBody ProdTypeRequest request) {
        String res = productController.addTypeInProd(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addAddInProd")
    public ResponseEntity<String> addAddInProd(@RequestBody ProdAddRequest request) {
        String res = productController.addAddInProd(request);
        return ResponseEntity.ok(res);
    }
}
