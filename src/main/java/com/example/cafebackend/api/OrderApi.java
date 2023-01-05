package com.example.cafebackend.api;

import com.example.cafebackend.controller.OrderController;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.table.Order;
import com.example.cafebackend.table.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderApi {

    private final OrderController orderController;

    public OrderApi(OrderController orderController) {
        this.orderController = orderController;
    }


    ///////////////////////////////////////////////////////////

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order res = orderController.createOrder(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getOrderById")
    public ResponseEntity<Optional<Order>> getOrderById(@RequestBody Order request) {
        Optional<Order> res = orderController.getOrderById(request.getOrderId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getAllOrder")
    public ResponseEntity<List<Order>> getOrderAll() {
        List<Order> res = orderController.getAllOrder();
        return ResponseEntity.ok(res);
    }


}
