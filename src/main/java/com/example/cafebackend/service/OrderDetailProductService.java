package com.example.cafebackend.service;

import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.OrderDetailProductRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderDetailProductService {

    private final OrderDetailProductRepository orderDetailProductRepository;

    public OrderDetailProductService(OrderDetailProductRepository orderDetailProductRepository) {
        this.orderDetailProductRepository = orderDetailProductRepository;
    }
    //////////////////////////

    public OrderDetailProduct createOrderDetailProduct(Order order) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ODTP" + uuid.substring(0, 14);
        OrderDetailProduct table = new OrderDetailProduct();
        /// save order
        table.setOdtProdId(uuid);
        table.setOrder(order);
        return orderDetailProductRepository.save(table);
    }
    /////////////////////////

    public OrderDetailProduct updaterOderDetailProduct(OrderDetailProduct orderDetailProduct) throws OrderException {
        /// verify
        if (Objects.isNull(orderDetailProduct))
            throw OrderException.updateFailDataNull();
        /// save prod record to database
        return orderDetailProductRepository.save(orderDetailProduct);
    }
    /////////////////////////

    public Optional<OrderDetailProduct> findById(String id) {
        ///
        return orderDetailProductRepository.findById(id);
    }
    /////////////////////////

    public List<OrderDetailProduct> findByOrderId(String id) {
        ///
        return orderDetailProductRepository.findAllByOrderOrderId(id);
    }
    /////////////////////////

    public List<OrderDetailProduct> findAllDetail() {
        ///
        return orderDetailProductRepository.findAll();
    }
    /////////////////////////

    public List<OrderDetailProduct> findByOrderDetailProductBetweenDate(String start, String end) {
        ///
        return orderDetailProductRepository.findOrderDetailProductBetweenDate(start, end);
    }

    public List<OrderDetailProduct> findByOrderDetailProductBetweenDateStatus(String start, String end, String status) {
        ///
        return orderDetailProductRepository.findOrderDetailProductBetweenDateStatus(start, end, status);
    }

    public Set<String> findByOrderDetailProductFormIdBetweenDateStatus(String start, String end, String status) {
        ///
        return orderDetailProductRepository.findOrderDetailProductFormIdBetweenDateStatus(start, end, status);
    }

    public Integer findByOrderDetailProductCountBetweenDateStatus(String start, String end, String status,
            String formId) {
        ///
        return orderDetailProductRepository.findOrderDetailProductCountBetweenDateStatus(start, end, status, formId);
    }
    /////////////////////////

    public void clearOderDetailProduct(OrderDetailProduct orderDetailProduct) {
        ///
        orderDetailProduct.setOrder(null);
        orderDetailProductRepository.save(orderDetailProduct);
    }
    /////////////////////////

    public void deleteOderDetailProduct(String orderDetailId) {
        ///
        orderDetailProductRepository.deleteById(orderDetailId);
    }
    /////////////////////////

}
