package com.example.cafebackend.api;

import com.example.cafebackend.controller.MaterialController;
import com.example.cafebackend.controller.MaterialUsedController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.model.request.UsedRequest;
import com.example.cafebackend.model.response.MessageResponse;

import com.example.cafebackend.table.Material;
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

    @PutMapping("/updateMaterial")
    public ResponseEntity<MessageResponse> updateMaterial(@RequestBody Material request) throws BaseException {
        MessageResponse res = materialController.updateMaterial(request.getMateId(), request.getMateName(), request.getStock(), request.getIsEnable(), request.getMateUnit());
        return ResponseEntity.ok(res);
    }
    ///////////////////////////////

    @GetMapping("/getMaterial")
    public ResponseEntity<MessageResponse> getMateById(@RequestParam(name = "mateId", required = false) String mateId) throws BaseException {
        MessageResponse res = materialController.getMaterial(mateId);
        return ResponseEntity.ok(res);
    }

//    @GetMapping("/getMaterialAll")
//    public ResponseEntity<MessageResponse> getMateAll() {
//        MessageResponse res = materialController.getMaterialAll();
//        return ResponseEntity.ok(res);
//    }
    ///////////////////////////////

    @DeleteMapping("/deleteMaterial")
    public ResponseEntity<MessageResponse> deleteMate(@RequestParam("mateId") String mateId) throws MaterialException {
        MessageResponse res = materialController.deleteMate(mateId);
        return ResponseEntity.ok(res);
    }




    //////////////////////////////////////////////////////////  material used

    @PostMapping("/createMaterialUseInto")
    public ResponseEntity<MessageResponse> updateMaterialUseOfBase(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.createMaterialUsed(usedRequest);
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/updateMaterialUseIntoProductForm")
//    public ResponseEntity<MessageResponse> updateMaterialUseOfForm(@RequestBody UsedRequest usedRequest)  throws Exception {
//        MessageResponse res = materialUsedController.updateAddMaterialUsedInForm(usedRequest.getProdFormId(), usedRequest.getMateUsed());
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/updateMaterialUseIntoOption")
//    public ResponseEntity<MessageResponse> updateMaterialUseOfOption(@RequestBody UsedRequest usedRequest)  throws Exception {
//        MessageResponse res = materialUsedController.updateAddMaterialUsedInOption(usedRequest.getProdFormId(), usedRequest.getMateUsed());
//        return ResponseEntity.ok(res);
//    }

    //////////////////////

//    @PostMapping("/getMaterialUseByMaterialId")
//    public ResponseEntity<MessageResponse> findMateUseByMateId(@RequestParam("mateId") String mateId) throws BaseException {
//        MessageResponse res = materialUsedController.findListMateUseByMateId(mateId);
//        return ResponseEntity.ok(res);
//    }

    @GetMapping("/getMaterialUse")
    public ResponseEntity<MessageResponse> findMaterialUseInBase(@RequestParam(name = "mateId" , required = false) String mateId,
                                                                 @RequestParam(name = "baseId" , required = false) String baseId,
                                                                 @RequestParam(name = "formId" , required = false) String formId,
                                                                 @RequestParam(name = "optionId" , required = false) String optionId)  throws Exception {
        MessageResponse res = materialUsedController.findMaterialUsed(mateId, baseId, formId, optionId);
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/getMaterialUseByProductFormId")
//    public ResponseEntity<MessageResponse> findMaterialUseInForm(@RequestParam("formId") String formId)  throws Exception {
//        MessageResponse res = materialUsedController.findMaterialUsedInFormId(formId);
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/getMaterialUseByOptionId")
//    public ResponseEntity<MessageResponse> findMaterialUseInOption(@RequestParam("optionId") String optionId)  throws Exception {
//        MessageResponse res = materialUsedController.findMaterialUsedInOptionId(optionId);
//        return ResponseEntity.ok(res);
//    }


}
