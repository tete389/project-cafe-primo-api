package com.example.cafebackend.api;

import com.example.cafebackend.controller.AddOnController;
import com.example.cafebackend.controller.OptionController;
import com.example.cafebackend.exception.BaseException;


import com.example.cafebackend.model.response.ForFindAddOnOpion.ForOptionResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductFormAddOnResponse;
import com.example.cafebackend.model.response.MessageResponse;

import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Option;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/addOn")
public class AddOnApi {

    private final AddOnController addOnController;

    private final OptionController optionController;


    public AddOnApi(AddOnController addOnController, OptionController optionController) {
        this.addOnController = addOnController;
        this.optionController = optionController;
    }


    //////////////////////////////////////////////////////////  AddOn

    @PostMapping("/createAddOn")
    public ResponseEntity<MessageResponse> createAddOn(@RequestBody AddOn request) throws BaseException {
        MessageResponse res = addOnController.createAddOn(request.getAddOnTitleTh(), request.getAddOnTitleEng(), request.getIsManyOptions(), request.getDescription());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateAddOn")
    public ResponseEntity<MessageResponse> updateAddOn(@RequestBody AddOn request) throws BaseException {
        MessageResponse res = addOnController.updateAddOn(request.getAddOnId(), request.getAddOnTitleTh(), request.getAddOnTitleEng(),request.getIsManyOptions(), request.getIsEnable(), request.getDescription());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateIntoProductForm")
    public ResponseEntity<MessageResponse> updateAddOnInProductForm(@RequestBody ForProductFormAddOnResponse request) throws Exception {
        MessageResponse res = addOnController.updateAddOnInProductForm(request.getProdFormId(), request.getAddOn());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/deleteFromProductForm")
    public ResponseEntity<MessageResponse> deleteAddOnFromProductForm(@RequestBody ForProductFormAddOnResponse request) throws Exception {
        MessageResponse res = addOnController.deleteAddOnFromProductForm(request.getProdFormId(), request.getAddOn());
        return ResponseEntity.ok(res);
    }

    //////////////////////////////////////////


    @GetMapping("/getAddOn")
    public ResponseEntity<MessageResponse> getAddById(@RequestParam(name = "addId" , required = false) String addId,
                                                      @RequestParam(name = "formId" , required = false) Long formId,
                                                      @RequestParam(name = "option", required = false) String option) throws BaseException {
        MessageResponse res = addOnController.findAddOn(addId, formId, option);
        return ResponseEntity.ok(res);
    }

    //////////////////////////////////////////

    @PostMapping("/deleteAddOn")
    public ResponseEntity<MessageResponse> deleteAdd(@RequestBody AddOn request) throws BaseException {
        MessageResponse res = addOnController.deleteAddOn(request.getAddOnId());
        return ResponseEntity.ok(res);
    }



    //////////////////////  Option

    @PostMapping("/option/createOption")
    public ResponseEntity<MessageResponse> createOption(@RequestBody ForOptionResponse request) throws BaseException {
        MessageResponse res = optionController.createOption(request.getAddOnId(), request.getOptionNameTh(), request.getOptionNameEng(), request.getPrice());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/option/updateOption")
    public ResponseEntity<MessageResponse> updateOptionInfo(@RequestBody Option request) throws BaseException {
        MessageResponse res = optionController.updateOption(request.getOptionId(), request.getOptionNameTh(), request.getOptionNameEng(), request.getPrice(), request.getIsEnable());
        return ResponseEntity.ok(res);
    }

    //////////////////////////////////////////

    @GetMapping("/option/getOption")
    public ResponseEntity<MessageResponse> getOptionById(@RequestParam(name = "optionId" , required = false) String optionId,
                                                         @RequestParam(name = "addOnId" , required = false) String addOnId) throws BaseException {
        MessageResponse res = optionController.findOption(optionId, addOnId);
        return ResponseEntity.ok(res);
    }


    //////////////////////////////////////////

    @PostMapping("/option/deleteOption")
    public ResponseEntity<MessageResponse> deleteOption(@RequestBody Option request) throws BaseException {
        MessageResponse res = optionController.deleteOption(request.getOptionId());
        return ResponseEntity.ok(res);
    }

}
