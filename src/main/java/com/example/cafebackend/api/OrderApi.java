package com.example.cafebackend.api;

import com.example.cafebackend.controller.OrderController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.model.request.DateRequest;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.table.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private final OrderController orderController;

    public OrderApi(OrderController orderController) {
        this.orderController = orderController;
    }

    ///////////////////////////////////////////////////////////

    @PostMapping("/createOrder")
    public ResponseEntity<MessageResponse> createOrder(@RequestBody OrderRequest request) throws Exception {
        MessageResponse res = orderController.createOrder(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateProductInOrder")
    public ResponseEntity<MessageResponse> updateProductInOrder(@RequestBody OrderRequest request) throws Exception {
        MessageResponse res = orderController.updateProdInOrder(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateConfirmPayment")
    public ResponseEntity<MessageResponse> updateConfirmPayment(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.updateOrderConfirmPayment(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateCancelOrder")
    public ResponseEntity<MessageResponse> updateCancelOrder(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.updateCancelOrder(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getOrderAll")
    public ResponseEntity<MessageResponse> getOrderAll() {
        MessageResponse res = orderController.getOrderAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getOrderToDay")
    public ResponseEntity<MessageResponse> getOrderToDay() {
        MessageResponse res = orderController.getOrderToDay();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getOrderById")
    public ResponseEntity<MessageResponse> getOrderById(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.getOrderById(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getOrderInfoById")
    public ResponseEntity<MessageResponse> getOrderInfoById(@RequestBody Order request) throws BaseException {
        MessageResponse res = orderController.getOrderInfoById(request.getOrderId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getRecentOrder")
    public ResponseEntity<MessageResponse> getRecentOrder(@RequestBody DateRequest dateRequest) throws BaseException {
        MessageResponse res = orderController.getRecentOrder(dateRequest.getDateStart(), dateRequest.getDateEnd(), dateRequest.getStatusOrder());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getRecentDetailProduct")
    public ResponseEntity<MessageResponse> getRecentDetailProduct(@RequestBody DateRequest dateRequest) throws BaseException {
        MessageResponse res = orderController.getRecentOrderDetailProduct(dateRequest.getDateStart(), dateRequest.getDateEnd());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getRecentDetailMaterial")
    public ResponseEntity<MessageResponse> getRecentDetailMaterial(@RequestBody DateRequest dateRequest) throws BaseException {
        MessageResponse res = orderController.getRecentOrderDetailMaterial(dateRequest.getDateStart(), dateRequest.getDateEnd());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getRecentDetailOption")
    public ResponseEntity<MessageResponse> getRecentDetailOption(@RequestBody DateRequest dateRequest) throws BaseException {
        MessageResponse res = orderController.getRecentOrderDetailOption(dateRequest.getDateStart(), dateRequest.getDateEnd());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteOder")
    public ResponseEntity<MessageResponse> deleteOder(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.deleteOrder(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

}
