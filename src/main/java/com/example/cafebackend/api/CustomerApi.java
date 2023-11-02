package com.example.cafebackend.api;

import com.example.cafebackend.controller.CustomerController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.request.OrderRequestCollect;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.model.response.ForOrder.OrderDiscount;

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
    public ResponseEntity<MessageResponse> collectPoints(@RequestBody OrderRequestCollect orderCollect)
            throws BaseException {
        MessageResponse res = customerController.collectPoints(orderCollect.getOrderId(),
                orderCollect.getCollectPoint());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/spendPoint")
    public ResponseEntity<MessageResponse> spendPoint(@RequestBody OrderDiscount spendPoint) throws BaseException {
        MessageResponse res = customerController.spendPoint(spendPoint.getPhoneNumber(), spendPoint.getSpendPoint());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getPoint")
    public ResponseEntity<MessageResponse> getPoint(@RequestParam(name = "phoneNumber") String phoneNumber)
            throws BaseException {
        MessageResponse res = customerController.getPoint(phoneNumber);
        return ResponseEntity.ok(res);
    }

}
