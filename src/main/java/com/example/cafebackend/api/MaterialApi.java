package com.example.cafebackend.api;

import com.example.cafebackend.controller.MaterialController;
import com.example.cafebackend.model.request.ProdCateRequest;
import com.example.cafebackend.model.request.ProdMateRequest;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mate")
public class MaterialApi {

    private final MaterialController materialController;

    public MaterialApi(MaterialController materialController) {
        this.materialController = materialController;
    }


    //////////////////////////////////////////////////////////

    @PostMapping("/createMate")
    public ResponseEntity<Material> createCate(@RequestBody Material request) {
        Material res = materialController.createMaterial(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAllMate")
    public ResponseEntity<List<Material>> getAllMate() {
        List<Material> res = materialController.getAllMate();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getMateById")
    public ResponseEntity<Material> getCateByCateId(@RequestBody Material request) {
        Material res = materialController.getMateById(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateMate")
    public ResponseEntity<Material> updateMate(@RequestBody Material request) {
        Material res = materialController.updateMate(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteMate")
    public ResponseEntity<String> deleteMate(@RequestBody Material request) {
        String res = materialController.deleteMate(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addProdInMate")
    public ResponseEntity<String> addProdInMate(@RequestBody ProdMateRequest request) {
        String res = materialController.addProductInMate(request);
        return ResponseEntity.ok(res);
    }
}
