package com.example.cafebackend.api;

import com.example.cafebackend.controller.MaterialController;
import com.example.cafebackend.controller.MaterialUsedController;
import com.example.cafebackend.exception.BaseException;

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
    public ResponseEntity<MessageResponse> createMate(@RequestBody Material request) throws BaseException {
        MessageResponse res = materialController.createMaterial(request.getMateName(), request.getMateUnit(), request.getStock());
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

    ///////////////////////////////

    @PostMapping("/deleteMaterial")
    public ResponseEntity<MessageResponse> deleteMate(@RequestBody Material request) throws BaseException {
        MessageResponse res = materialController.deleteMate(request.getMateId());
        return ResponseEntity.ok(res);
    }




    //////////////////////////////////////////////////////////  material used

    @PostMapping("/createMaterialUseInto")
    public ResponseEntity<MessageResponse> updateMaterialUseOfBase(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.createMaterialUsed(usedRequest);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/removeMaterialUseInto")
    public ResponseEntity<MessageResponse> MaterialUseOfBase(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.removeMaterialUsed(usedRequest);
        return ResponseEntity.ok(res);
    }



    @GetMapping("/getMaterialUse")
    public ResponseEntity<MessageResponse> findMaterialUseInBase(@RequestParam(name = "mateId" , required = false) String mateId,
                                                                 @RequestParam(name = "baseId" , required = false) String baseId,
                                                                 @RequestParam(name = "formId" , required = false) Long formId,
                                                                 @RequestParam(name = "optionId" , required = false) String optionId)  throws Exception {
        MessageResponse res = materialUsedController.findMaterialUsed(mateId, baseId, formId, optionId);
        return ResponseEntity.ok(res);
    }



}
