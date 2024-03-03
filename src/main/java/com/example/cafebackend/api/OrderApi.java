package com.example.cafebackend.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafebackend.controller.OrderController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.model.request.OrderRequestCollect;
import com.example.cafebackend.model.response.EmployeeNotifications;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.model.response.ForOrder.ForRequestListOrder;
import com.example.cafebackend.table.Order;

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
        setEmployeeNotification();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateProductInOrder")
    public ResponseEntity<MessageResponse> updateProductInOrder(@RequestBody OrderRequest request) throws Exception {
        MessageResponse res = orderController.updateProdInOrder(request);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateStatusOrder")
    public ResponseEntity<MessageResponse> updateConfirmPayment(@RequestBody OrderRequestCollect order)
            throws BaseException {
        List<String> statusOrder = new ArrayList<>();
        MessageResponse res = orderController.updateOrderConfirm(order.getOrderId(), order.getStatus(),
                order.getCollectPoint(), statusOrder);
        setEmployeeNotification();
        setCustomerNotification(order.getOrderId());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getOrderById")
    public ResponseEntity<MessageResponse> getOrderInfoById(@RequestParam(name = "orderId") String orderId,
            @RequestParam(name = "orderDetail", required = false) String orderDetail) throws BaseException {
        MessageResponse res = orderController.getOrderInfoById(orderId, orderDetail);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getOrderByListId")
    public ResponseEntity<MessageResponse> getOrderInfoByListId(@RequestBody ForRequestListOrder orderIds)
            throws BaseException {
        MessageResponse res = orderController.getOrderInfoByListId(orderIds);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getRecentOrder")
    public ResponseEntity<MessageResponse> getRecentOrder(
            @RequestParam(name = "recentMaterial", required = false) String recentMaterial,
            @RequestParam(name = "recentProduct", required = false) String recentProduct,
            @RequestParam(name = "recentOption", required = false) String recentOption,
            @RequestParam(name = "dateStart") String dateStart,
            @RequestParam(name = "dateEnd") String dateEnd,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "income", required = false) String income,
            @RequestParam(name = "pageSize ", required = false) Integer pageSize,
            @RequestParam(name = "pageNum", required = false) Integer pageNum) throws BaseException {
        MessageResponse res = orderController.getRecentOrder(recentMaterial, recentProduct, recentOption, dateStart,
                dateEnd, status, income, pageSize, pageNum);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteOder")
    public ResponseEntity<MessageResponse> deleteOder(@RequestBody Order orderId) throws BaseException {
        MessageResponse res = orderController.deleteOrder(orderId.getOrderId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getEmployeeNotifications")
    public ResponseEntity<?> sendEmployeeNotifications() throws BaseException {
        setEmployeeNotification();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getCustomerNotifications")
    public ResponseEntity<?> sendCustomerNotifications() throws BaseException {
        // setCustomerNotification();
        return ResponseEntity.ok().build();
    }

    public void setEmployeeNotification() {
        EmployeeNotifications empRes = orderController.countOrderStatus();
        messagingTemplate.convertAndSend("/topic/employee_notifications", empRes);

    }

    public void setCustomerNotification(String orderId) {
        messagingTemplate.convertAndSend("/topic/customer_notifications", orderId);

    }

    // @PostMapping("/getCustomerNotifications")
    // public ResponseEntity<?> sendCustomerNotifications() throws BaseException {

    // return ResponseEntity.ok().build();
    // }

}
