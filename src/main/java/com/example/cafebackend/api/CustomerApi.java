package com.example.cafebackend.api;

import com.example.cafebackend.controller.CustomerController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerApi {

    private final CustomerController customerController;

    public CustomerApi(CustomerController customerController) {
        this.customerController = customerController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/collectPoints")
    public ResponseEntity<MessageResponse> collectPoints(@RequestParam(name = "phoneNumber") String phoneNumber,
                                                         @RequestParam(name = "orderId") String orderId) throws BaseException {
        MessageResponse res = customerController.collectPoints(phoneNumber, orderId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/spendPoint")
    public ResponseEntity<MessageResponse> spendPoint(@RequestParam(name = "phoneNumber") String phoneNumber,
                                                      @RequestParam(name = "orderId") String orderId) throws BaseException {
        MessageResponse res = customerController.spendPoint(phoneNumber, orderId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getPoint")
    public ResponseEntity<MessageResponse> getPoint(@RequestParam(name = "phoneNumber") String phoneNumber) throws BaseException {
        MessageResponse res = customerController.getPoint(phoneNumber);
        return ResponseEntity.ok(res);
    }

}
