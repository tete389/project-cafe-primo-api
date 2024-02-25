package com.example.cafebackend.service;

import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.OrderDetailMaterialRepository;
import com.example.cafebackend.table.OrderDetailMaterial;
import com.example.cafebackend.table.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderDetailMaterialService {

    private final OrderDetailMaterialRepository orderDetailMaterialRepository;

    public OrderDetailMaterialService(OrderDetailMaterialRepository orderDetailMaterialRepository) {
        this.orderDetailMaterialRepository = orderDetailMaterialRepository;
    }

    //////////////////////////

    public OrderDetailMaterial createOrderDetailMaterial(Order order, OrderDetailMaterial orderDetailMaterial) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ODM" + uuid.substring(0, 14);
        OrderDetailMaterial table = new OrderDetailMaterial();
        /// save order
        table.setOdtMateId(uuid);
        table.setOrder(order);
        return orderDetailMaterialRepository.save(table);
    }
    /////////////////////////

    public OrderDetailMaterial updateOrderDetailMaterial(OrderDetailMaterial mateRecord) throws OrderException {
        /// verify
        if (Objects.isNull(mateRecord))
            throw OrderException.updateFailDataNull();
        /// save prod record to database
        return orderDetailMaterialRepository.save(mateRecord);
    }
    /////////////////////////

    public Optional<OrderDetailMaterial> findById(String id) {
        ///
        return orderDetailMaterialRepository.findById(id);
    }
    /////////////////////////

    public List<OrderDetailMaterial> findByOrderId(String id) {
        ///
        return orderDetailMaterialRepository.findAllByOrderOrderId(id);
    }
    /////////////////////////

    public List<OrderDetailMaterial> findAllDetail() {
        ///
        return orderDetailMaterialRepository.findAll();
    }
    /////////////////////////

    public List<OrderDetailMaterial> findByOrderDetailMaterialBetweenDate(String start, String end) {
        ///
        return orderDetailMaterialRepository.findOrderDetailMaterialBetweenDate(start, end);
    }

    public List<OrderDetailMaterial> findByOrderDetailMaterialBetweenDateStatus(String start, String end,
            String status) {
        ///
        return orderDetailMaterialRepository.findOrderDetailMaterialBetweenDateStatus(start, end, status);
    }

    public Set<List<String>> findByOrderDetailMaterialNameBetweenDateStatus(String start, String end, String status) {
        ///
        return orderDetailMaterialRepository.findOrderDetailMaterialNameBetweenDateStatus(start, end, status);
    }

    public Set<List<String>> findByOrderDetailMaterialNameBetweenDate(String start, String end) {
        ///
        return orderDetailMaterialRepository.findOrderDetailMaterialNameBetweenDate(start, end);
    }

    public Integer findByOrderDetailMaterialCountBetweenDateStatus(String start, String end, String status,
            String name) {
        ///
        return orderDetailMaterialRepository.findOrderDetailMaterialCountBetweenDateStatus(start, end, status, name);
    }

    public Integer findByOrderDetailMaterialCountBetweenDate(String start, String end,
            String name) {
        ///
        return orderDetailMaterialRepository.findOrderDetailMaterialCountBetweenDate(start, end, name);
    }
    /////////////////////////

    public void clearOrderDetailMaterial(OrderDetailMaterial mateRecord) {
        ///
        mateRecord.setOrder(null);
        orderDetailMaterialRepository.save(mateRecord);
    }
    /////////////////////////

    public void deleteOrderDetailMaterial(String orderDetailId) {
        ///
        orderDetailMaterialRepository.deleteById(orderDetailId);
    }
    /////////////////////////

}
