package com.example.cafebackend.api;

import com.example.cafebackend.controller.MaterialController;
import com.example.cafebackend.controller.MaterialUsedController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.model.request.MateUsedRequest;
import com.example.cafebackend.model.request.UsedRequest;
import com.example.cafebackend.model.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/material")
public class MaterialApi {

    private final MaterialController materialController;

    private final MaterialUsedController materialUsedController;

    public MaterialApi(MaterialController materialController, MaterialUsedController materialUsedController) {
        this.materialController = materialController;
        this.materialUsedController = materialUsedController;
    }


    //////////////////////////////////////////////////////////

    @PostMapping("/createMaterial")
    public ResponseEntity<MessageResponse> createMate(@RequestParam("mateName") String mateName,
                                                      @RequestParam("mateStock") Double mateStock,
                                                      @RequestParam("mateUnit") String unit) throws BaseException {
        MessageResponse res = materialController.createMaterial(mateName, mateStock, unit);
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

    @PostMapping("/updateMaterial")
    public ResponseEntity<MessageResponse> updateMaterial(@RequestParam("mateId") String mateId,
                                                          @RequestParam("mateName") String mateName,
                                                          @RequestParam("stock") String stock,
                                                          @RequestParam("enable") String enable) throws BaseException {
        MessageResponse res = materialController.updateMaterial(mateId, mateName, stock, enable);
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

    @PostMapping("/updateAddMaterialUseToForm")
    public ResponseEntity<MessageResponse> updateAddMaterialUseToForm(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.updateAddMaterialUsedToForm(usedRequest.getProdFormId(), usedRequest.getMateUsed());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteMate")
    public ResponseEntity<MessageResponse> deleteMate(@RequestParam("mateId") String mateId) throws MaterialException {
        MessageResponse res = materialController.deleteMate(mateId);
        return ResponseEntity.ok(res);
    }

}
