package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.mapper.OrderMapper;
import com.example.cafebackend.mapper.ProductRecordMapper;
import com.example.cafebackend.model.request.OrderRequest;
import com.example.cafebackend.model.request.ProdRequest;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.ProductRecordService;
import com.example.cafebackend.service.OrderService;
import com.example.cafebackend.table.Order;
import com.example.cafebackend.table.ProductRecord;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Service
public class OrderController {

    private OrderService orderService;

    //private ProductRecordService productRecordService;

    private ProductRecordController productRecordController;

    private OrderMapper orderMapper;

    private ProductRecordMapper productRecordMapper;

    ////////////////////////////////////////////////
    public List<Order> getAllOrder(){
        ///
        return orderService.findAllOrder();
    }

    ////////////////////////////////////////////////
    public Order createOrder(OrderRequest request) throws BaseException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Integer countOrderToDay = orderService.findByOrderToDay(timeStamp);
        String no_Order = countOrderToDay < 9 ? "#0" + countOrderToDay + 1 : "#" + (countOrderToDay + 1);
        /// add no. order and status
        Order order = orderService.createOrder(no_Order, "Doing Created");
        /// total price
        double totalPrices = 0;
        /// total point
        double totalBonusPoint = 0;
        /// check prod request
        for(ProdRequest prodRequest : request.getProdRequests()){
            /// create prod record
            ProductRecord productRecord = productRecordController.createProductRecord(order, prodRequest);
            /// check prod price
            totalPrices = totalPrices + productRecord.getTotalPrice();
            /// check prod bonus point
            totalBonusPoint = totalBonusPoint + productRecord.getBonusPoint();
            /// add prod record
            order.getProductRecords().add(productRecord);
        }
        /// add data
        order.setTotalPrice(totalPrices);
        order.setTotalBonusPoint(totalBonusPoint);
        order.setNote(request.getNote());
        order.setStatus("Wait Payment Confirm");

        return orderService.updateOrder(order);
    }
    ////////////////////////////////////////////////

    public Order updateProdInOrder(OrderRequest request) throws BaseException {
        Optional<Order> orderOpt = orderService.findById(request.getOrderId());
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()) throw OrderException.updateFailDataNull();
        Order order =  orderOpt.get();
        /// add status and clear old data
        boolean clearProdRecord =  productRecordController.clearProductRecordInOrder(order.getOrderId());
        if(!clearProdRecord) throw OrderException.updateFailDataNull();
        Order oderEdit =  orderService.clearOrder(order, "Doing Edit");
        /// new total price
        double totalPrices = 0;
        /// new total point
        double totalBonusPoint = 0;
        /// check new prod request
        for(ProdRequest prodRequest : request.getProdRequests()){
            /// create prod record
            ProductRecord productRecord = productRecordController.createProductRecord(oderEdit, prodRequest);
            /// check prod price
            totalPrices = totalPrices + productRecord.getTotalPrice();
            /// check prod bonus point
            totalBonusPoint = totalBonusPoint + productRecord.getBonusPoint();
            /// add prod record
            oderEdit.getProductRecords().add(productRecord);
        }
        /// add data
        oderEdit.setTotalPrice(totalPrices);
        oderEdit.setTotalBonusPoint(totalBonusPoint);
        oderEdit.setNote(request.getNote());
        oderEdit.setStatus("Wait Payment Confirm");

        return orderService.updateOrder(oderEdit);
    }
    ////////////////////////////////////////////////

    public MessageResponse getOrderById(String id) throws OrderException {

        if (Objects.isNull(id)) throw OrderException.findFail();
        Optional<Order> orderOpt = orderService.findById(id);
        //if(orderOpt.isEmpty()) throw OrderException.findFail();

        MessageResponse res = new MessageResponse();
        if (Objects.isNull(orderOpt) || orderOpt.isEmpty()){
            res.setMessage("Get Order ById is Null");
            return res;
        }
        Order order = orderOpt.get();

//        List<ProductDetailCustomerResponse> detailMapperList = new ArrayList<>();
//        for (ProductDetail productDetail : order.getProductDetails()){
//            ProductDetailCustomerResponse detailMapper = productDetailMapper.toProductDetailCustomerResponse(productDetail);
//            detailMapperList.add(detailMapper);
//        }
//        OrderCustomerResponse orderCusRes = new OrderCustomerResponse();
//        orderCusRes.setOrderId(order.getOrderId());
//        orderCusRes.setOrderNumber(order.getOrderNumber());
//        orderCusRes.setOrderCreateDate(order.getOrderCreateDate());
//        orderCusRes.setOrderStatus(order.getOrderStatus());
//        orderCusRes.setOrderTotalPrice(order.getOrderTotalPrice());
//        orderCusRes.getProductDetails().addAll(detailMapperList);


        //OrderResponse orderRes = orderMapper.toOrderResponse(order.get());

        //OrderCustomerResponse orderCusRes = orderMapper.toOrderCustomerResponse(orderOpt.get());
        res.setMessage("Get Order ById success");
        res.setRes(order);
        return res;
    }


}
