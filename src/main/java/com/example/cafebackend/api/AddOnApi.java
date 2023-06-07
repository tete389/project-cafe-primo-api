package com.example.cafebackend.api;

import com.example.cafebackend.controller.AddOnController;
import com.example.cafebackend.controller.OptionController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.model.response.MessageResponse;

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
    public ResponseEntity<MessageResponse> createAddOn(@RequestParam("addOnTitle") String title,
                                                       @RequestParam("isManyOptions") Boolean manyOptions,
                                                       @RequestParam("description") String description ) throws BaseException {
        MessageResponse res = addOnController.createAddOn(title, manyOptions, description);
        return ResponseEntity.ok(res);
    }

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

    @PostMapping("/updateTitle")
    public ResponseEntity<MessageResponse> updateTitle(@RequestParam("addOnId") String addOnId,
                                                       @RequestParam("title") String newTitle) throws BaseException {
        MessageResponse res = addOnController.setAddOnTitle(addOnId, newTitle);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateManyOptions")
    public ResponseEntity<MessageResponse> updateManyOptions(@RequestParam("addOnId") String addOnId,
                                                             @RequestParam("manyOptions") Boolean manyOptions) throws BaseException {
        MessageResponse res = addOnController.setAddOnManyOptions(addOnId, manyOptions);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateEnable")
    public ResponseEntity<MessageResponse> updateEnable(@RequestParam("addOnId") String addOnId,
                                                        @RequestParam("enable") Boolean enable) throws BaseException {
        MessageResponse res = addOnController.setAddOnEnable(addOnId, enable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateDescription")
    public ResponseEntity<MessageResponse> updateDescription(@RequestParam("addOnId") String addOnId,
                                                             @RequestParam("description") String description) throws BaseException {
        MessageResponse res = addOnController.setAddOnDescription(addOnId, description);
        return ResponseEntity.ok(res);
    }

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

    @PostMapping("/option/getOptionById")
    public ResponseEntity<MessageResponse> getOptionById(@RequestParam("optionId") String optionId) throws BaseException {
        MessageResponse res = optionController.findOptionById(optionId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/getOptionAll")
    public ResponseEntity<MessageResponse> getOptionAll() throws BaseException {
        MessageResponse res = optionController.findAllOption();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/updateOptionInfo")
    public ResponseEntity<MessageResponse> updateOptionInfo(@RequestParam("optionId") String optionId,
                                                            @RequestParam("optionName") String optionName) throws BaseException {
        MessageResponse res = optionController.setOptionInfo(optionId, optionName);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/updatePrice")
    public ResponseEntity<MessageResponse> updatePrice(@RequestParam("optionId") String optionId,
                                                       @RequestParam("price") Double price) throws BaseException {
        MessageResponse res = optionController.setOptionPrice(optionId, price);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/updateForSale")
    public ResponseEntity<MessageResponse> updateForSale(@RequestParam("optionId") String optionId,
                                                         @RequestParam("forSale") Boolean forSale) throws BaseException {
        MessageResponse res = optionController.setForSale(optionId, forSale);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/option/deleteOption")
    public ResponseEntity<MessageResponse> deleteOption(@RequestParam("optionId") String optionId) throws BaseException {
        MessageResponse res = optionController.deleteOption(optionId);
        return ResponseEntity.ok(res);
    }

}
