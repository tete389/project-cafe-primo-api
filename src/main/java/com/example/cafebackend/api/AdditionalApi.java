package com.example.cafebackend.api;

import com.example.cafebackend.controller.AdditionalController;
import com.example.cafebackend.model.request.ProdAddRequest;
import com.example.cafebackend.model.request.ProdCateRequest;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/add")
public class AdditionalApi {

    private final AdditionalController additionalController;

    public AdditionalApi(AdditionalController additionalController) {
        this.additionalController = additionalController;
    }


    //////////////////////////////////////////////////////////

    @PostMapping("/createAdd")
    public ResponseEntity<Additional> createCate(@RequestBody Additional request) {
        Additional res = additionalController.createAdditional(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAllAdd")
    public ResponseEntity<List<Additional>> getAllAdd() {
        List<Additional> res = additionalController.getAllAdd();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAddById")
    public ResponseEntity<Additional> getAddById(@RequestBody Additional request) {
        Additional res = additionalController.getAddById(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateAdd")
    public ResponseEntity<Additional> updateAdd(@RequestBody Additional request) {
        Additional res = additionalController.updateAdd(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteAdd")
    public ResponseEntity<String> deleteAdd(@RequestBody Additional request) {
        String res = additionalController.deleteMate(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addProdInAdd")
    public ResponseEntity<String> addProdInAdd(@RequestBody ProdAddRequest request) {
        String res = additionalController.addProductInAdd(request);
        return ResponseEntity.ok(res);
    }
}
