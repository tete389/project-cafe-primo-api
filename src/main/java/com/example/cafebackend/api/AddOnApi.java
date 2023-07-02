package com.example.cafebackend.api;

import com.example.cafebackend.controller.AddOnController;
import com.example.cafebackend.controller.MaterialUsedController;
import com.example.cafebackend.controller.OptionController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.model.request.UsedRequest;
import com.example.cafebackend.model.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/addOn")
public class AddOnApi {

    private final AddOnController addOnController;

    private final OptionController optionController;

    private final MaterialUsedController materialUsedController;

    public AddOnApi(AddOnController addOnController, OptionController optionController, MaterialUsedController materialUsedController) {
        this.addOnController = addOnController;
        this.optionController = optionController;
        this.materialUsedController = materialUsedController;
    }


    //////////////////////////////////////////////////////////  AddOn

    @PostMapping("/createAddOn")
    public ResponseEntity<MessageResponse> createAddOn(@RequestParam("addOnTitle") String title,
                                                       @RequestParam("isManyOptions") Boolean manyOptions,
                                                       @RequestParam("description") String description ) throws BaseException {
        MessageResponse res = addOnController.createAddOn(title, manyOptions, description);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateAddOn")
    public ResponseEntity<MessageResponse> updateAddOn(@RequestParam("addOnId") String addOnId,
                                                       @RequestParam("addOnTitle") String addOnTitle,
                                                       @RequestParam("isManyOptions") String isManyOptions,
                                                       @RequestParam("isEnable") String isEnable,
                                                       @RequestParam("description") String description) throws BaseException {
        MessageResponse res = addOnController.updateAddOn(addOnId, addOnTitle, isManyOptions, isEnable, description);
        return ResponseEntity.ok(res);
    }
    //////////////////////////////////////////

    @GetMapping("/getAddOnAll")
    public ResponseEntity<MessageResponse> getAddOnAll() {
        MessageResponse res = addOnController.findAllAddOn();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAddOnById")
    public ResponseEntity<MessageResponse> getAddById(@RequestParam("addOnId") String AddOnId) throws BaseException {
        MessageResponse res = addOnController.findAddOnById(AddOnId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAddOnInfoAll")
    public ResponseEntity<MessageResponse> getAddOnInfoAll() {
        MessageResponse res = addOnController.findAllAddOnInfo();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAddOnInfoById")
    public ResponseEntity<MessageResponse> getAddInfoById(@RequestParam("addOnId") String AddOnId) throws BaseException {
        MessageResponse res = addOnController.findAddOnInfoById(AddOnId);
        return ResponseEntity.ok(res);
    }
    //////////////////////////////////////////

    @PostMapping("/deleteAddOn")
    public ResponseEntity<MessageResponse> deleteAdd(@RequestParam("addOnId") String addOnId) throws BaseException {
        MessageResponse res = addOnController.deleteAddOn(addOnId);
        return ResponseEntity.ok(res);
    }



    //////////////////////  Option

    @PostMapping("/option/createOption")
    public ResponseEntity<MessageResponse> createOption(@RequestParam("addOnId") String addOnId,
                                                        @RequestParam("optionName") String optionName,
                                                        @RequestParam("price") Double price) throws BaseException {
        MessageResponse res = optionController.createOption(addOnId, optionName, price);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/updateOption")
    public ResponseEntity<MessageResponse> updateOptionInfo(@RequestParam("optionId") String optionId,
                                                            @RequestParam("optionName") String optionName,
                                                            @RequestParam("price") String price,
                                                            @RequestParam("isEnable") String isEnable) throws BaseException {
        MessageResponse res = optionController.updateOption(optionId, optionName, price, isEnable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/updateMaterialUseIntoOption")
    public ResponseEntity<MessageResponse> updateMaterialUseOfOption(@RequestBody UsedRequest usedRequest)  throws Exception {
        MessageResponse res = materialUsedController.updateAddMaterialUsedInOption(usedRequest.getProdFormId(), usedRequest.getMateUsed());
        return ResponseEntity.ok(res);
    }
    //////////////////////////////////////////

    @PostMapping("/option/getOptionById")
    public ResponseEntity<MessageResponse> getOptionById(@RequestParam("optionId") String optionId) throws BaseException {
        MessageResponse res = optionController.findOptionById(optionId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/option/getOptionAll")
    public ResponseEntity<MessageResponse> getOptionAll() throws BaseException {
        MessageResponse res = optionController.findAllOption();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/getMaterialUseByOption")
    public ResponseEntity<MessageResponse> findMaterialUseInOption(@RequestParam("optionId") String optionId)  throws Exception {
        MessageResponse res = materialUsedController.findMaterialUsedInOptionId(optionId);
        return ResponseEntity.ok(res);
    }
    //////////////////////////////////////////

    @PostMapping("/option/deleteOption")
    public ResponseEntity<MessageResponse> deleteOption(@RequestParam("optionId") String optionId) throws BaseException {
        MessageResponse res = optionController.deleteOption(optionId);
        return ResponseEntity.ok(res);
    }

}
