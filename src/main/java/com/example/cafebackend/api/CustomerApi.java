package com.example.cafebackend.api;

import com.example.cafebackend.controller.CustomerController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.request.ManagePointsRequest;
import com.example.cafebackend.model.response.ApiResponse;
import com.example.cafebackend.model.response.EmployeeResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.table.Employee;
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
    public ResponseEntity<MessageResponse> collectPoints(@RequestBody ManagePointsRequest request) throws BaseException {
        MessageResponse res = customerController.collectPoints(request.getPhoneNumber() ,request.getPoint(), request.getOrderId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/spendPoint")
    public ResponseEntity<MessageResponse> spendPoint(@RequestBody ManagePointsRequest request) throws BaseException {
        MessageResponse res = customerController.spendPoint(request.getPhoneNumber() ,request.getPoint());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getPoint")
    public ResponseEntity<MessageResponse> getPoint(@RequestBody ManagePointsRequest request) throws BaseException {
        MessageResponse res = customerController.getPoint(request.getPhoneNumber());
        return ResponseEntity.ok(res);
    }

}
