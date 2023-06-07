package com.example.cafebackend.api;

import com.example.cafebackend.controller.OrderController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.exception.OrderException;
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
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) throws BaseException {
        Order res = orderController.createOrder(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getOrderById")
    public ResponseEntity<MessageResponse> getOrderById(@RequestBody String orderId) throws OrderException {
        MessageResponse res = orderController.getOrderById(orderId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<List<Order>> getOrderAll() {
        List<Order> res = orderController.getAllOrder();
        return ResponseEntity.ok(res);
    }

//    @PostMapping("/editOrderById")
//    public ResponseEntity<Order> editOrderById(@RequestBody OrderRequest request) throws BaseException {
//        Order res = orderController.editOrder(request);
//        return ResponseEntity.ok(res);
//    }


}
