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

    @PostMapping("/updateMaterial")
    public ResponseEntity<MessageResponse> updateMaterial(@RequestParam("mateId") String mateId,
                                                          @RequestParam("mateName") String mateName,
                                                          @RequestParam("stock") String stock,
                                                          @RequestParam("enable") String enable,
                                                          @RequestParam("unit") String unit) throws BaseException {
        MessageResponse res = materialController.updateMaterial(mateId, mateName, stock, enable, unit);
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

    @PostMapping("/getListMateUseByMateId")
    public ResponseEntity<MessageResponse> findListMateUseByMateId(@RequestParam("mateId") String mateId) throws BaseException {
        MessageResponse res = materialController.findListMateUseByMateId(mateId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteMaterial")
    public ResponseEntity<MessageResponse> deleteMate(@RequestParam("mateId") String mateId) throws MaterialException {
        MessageResponse res = materialController.deleteMate(mateId);
        return ResponseEntity.ok(res);
    }


    ////////////////////////////////////// mate use
    @PostMapping("/updateMaterialUseInBase")
    public ResponseEntity<MessageResponse> updateMaterialUseOfBase(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.updateAddMaterialUsedInBase(usedRequest.getProdFormId(), usedRequest.getMateUsed());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateMaterialUseInForm")
    public ResponseEntity<MessageResponse> updateMaterialUseOfForm(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.updateAddMaterialUsedInForm(usedRequest.getProdFormId(), usedRequest.getMateUsed());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateMaterialUseInOption")
    public ResponseEntity<MessageResponse> updateMaterialUseOfOption(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.updateAddMaterialUsedInOption(usedRequest.getProdFormId(), usedRequest.getMateUsed());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getMaterialUseInBase")
    public ResponseEntity<MessageResponse> findMaterialUseInBase(@RequestParam("baseId") String baseId)  throws Exception {
        MessageResponse res = materialUsedController.findMaterialUsedInBaseId(baseId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getMaterialUseInForm")
    public ResponseEntity<MessageResponse> findMaterialUseInForm(@RequestParam("formId") String formId)  throws Exception {
        MessageResponse res = materialUsedController.findMaterialUsedInFormId(formId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getMaterialUseInOption")
    public ResponseEntity<MessageResponse> findMaterialUseInOption(@RequestParam("optionId") String optionId)  throws Exception {
        MessageResponse res = materialUsedController.findMaterialUsedInOptionId(optionId);
        return ResponseEntity.ok(res);
    }

}
