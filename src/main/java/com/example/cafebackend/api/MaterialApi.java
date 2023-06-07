package com.example.cafebackend.api;

import com.example.cafebackend.controller.MaterialController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.model.response.MessageResponse;

import com.example.cafebackend.table.Material;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/material")
public class MaterialApi {

    private final MaterialController materialController;

    public MaterialApi(MaterialController materialController) {
        this.materialController = materialController;
    }


    //////////////////////////////////////////////////////////

    @PostMapping("/createMaterial")
    public ResponseEntity<MessageResponse> createMate(@RequestParam("mateName") String mateName,
                                                      @RequestParam("mateStock") Double mateStock) throws BaseException {
        MessageResponse res = materialController.createMaterial(mateName, mateStock);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getMaterialById")
    public ResponseEntity<MessageResponse> getMateById(@RequestParam("mateId") String mateId) throws BaseException {
        MessageResponse res = materialController.getMaterialById(mateId);
        return ResponseEntity.ok(res);
    }



    @GetMapping("/getMaterialAll")
    public ResponseEntity<MessageResponse> getMateAll() {
        MessageResponse res = materialController.getMaterialAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getListProductByMaterialId")
    public ResponseEntity<MessageResponse> findListProductByMaterialId(@RequestParam("mateId") String mateId) throws BaseException {
        MessageResponse res = materialController.findListProductByMaterialId(mateId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateName")
    public ResponseEntity<MessageResponse> updateName(@RequestParam("mateId") String mateId,
                                                      @RequestParam("mateName") String mateName) throws BaseException {
        MessageResponse res = materialController.setMaterialName(mateId, mateName);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateEnable")
    public ResponseEntity<MessageResponse> updateEnable(@RequestParam("mateId") String mateId,
                                                        @RequestParam("enable") Boolean enable) throws BaseException {
        MessageResponse res = materialController.setMaterialEnable(mateId, enable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateStock")
    public ResponseEntity<MessageResponse> updateStock(@RequestParam("mateId") String mateId,
                                                       @RequestParam("stock") Double stock)  throws BaseException {
        MessageResponse res = materialController.setMaterialStock(mateId, stock);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteMate")
    public ResponseEntity<MessageResponse> deleteMate(@RequestParam("mateId") String mateId) throws MaterialException {
        MessageResponse res = materialController.deleteMate(mateId);
        return ResponseEntity.ok(res);
    }

}
