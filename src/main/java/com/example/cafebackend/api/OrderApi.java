package com.example.cafebackend.api;

import com.example.cafebackend.controller.OrderController;
import com.example.cafebackend.exception.BaseException;

import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.table.Order;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin(origins = {"http://localhost:5137"})
@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final OrderController orderController;

    public OrderApi(OrderController orderController) {
        this.orderController = orderController;
    }

    ///////////////////////////////////////////////////////////

    @PostMapping("/createOrder")
    public ResponseEntity<MessageResponse> createOrder(@RequestBody OrderRequest request) throws Exception {
        MessageResponse res = orderController.createOrder(request);
        String countOrderNotPayment = orderController.countOrderNotPayment();
        messagingTemplate.convertAndSend("/topic/notifications", countOrderNotPayment);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateProductInOrder")
    public ResponseEntity<MessageResponse> updateProductInOrder(@RequestBody OrderRequest request) throws Exception {
        MessageResponse res = orderController.updateProdInOrder(request);
        return ResponseEntity.ok(res);
    }

    // @CrossOrigin(origins = "*", allowedHeaders = "*")
    @CrossOrigin
    @PutMapping("/confirmOrder")
    public ResponseEntity<MessageResponse> updateConfirmPayment(@RequestBody Order order) throws BaseException {
        MessageResponse res = orderController.updateOrderConfirm(order.getOrderId(), order.getStatus());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/cancelOrder")
    public ResponseEntity<MessageResponse> updateCancelOrder(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.updateCancelOrder(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getOrderById")
    public ResponseEntity<MessageResponse> getOrderInfoById(@RequestParam(name = "orderId") String orderId,
            @RequestParam(name = "orderDetail", required = false) String orderDetail) throws BaseException {
        MessageResponse res = orderController.getOrderInfoById(orderId, orderDetail);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getRecentOrder")
    public ResponseEntity<MessageResponse> getRecentOrder(
            @RequestParam(name = "recentMaterial", required = false) String recentMaterial,
            @RequestParam(name = "recentProduct", required = false) String recentProduct,
            @RequestParam(name = "recentOption", required = false) String recentOption,
            @RequestParam(name = "dateStart") String dateStart,
            @RequestParam(name = "dateEnd") String dateEnd,
            @RequestParam(name = "status") String status) throws BaseException {
        MessageResponse res = orderController.getRecentOrder(recentMaterial, recentProduct, recentOption, dateStart,
                dateEnd, status);

        // if (Objects.isNull(recentMaterial) || Objects.isNull(recentProduct) ||
        // Objects.isNull(recentOption) || recentMaterial.isEmpty() ||
        // recentProduct.isEmpty() || recentOption.isEmpty())) {
        // String countOrderNotPayment = orderController.countOrderNotPayment();
        // messagingTemplate.convertAndSend("/topic/notifications",
        // countOrderNotPayment);
        // }
        // String countOrderNotPayment = orderController.countOrderNotPayment();
        // messagingTemplate.convertAndSend("/topic/notifications", countOrderNotPayment);
        return ResponseEntity.ok(res);
    }

    
    @GetMapping("/getNotifications")
    public ResponseEntity<?> sendNotifications() throws BaseException {
        String countOrderNotPayment = orderController.countOrderNotPayment();
        messagingTemplate.convertAndSend("/topic/notifications", countOrderNotPayment);
        // MessageResponse res = new MessageResponse();
        // res.setMessage("send success");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteOder")
    public ResponseEntity<MessageResponse> deleteOder(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.deleteOrder(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

}
