package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.repository.OrderDetailPointRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDetailPointService {

    private final OrderDetailPointRepository orderDetailPointRepository;

    public OrderDetailPointService(OrderDetailPointRepository orderDetailPointRepository) {
        this.orderDetailPointRepository = orderDetailPointRepository;
    }

    //////////////////////////

    public OrderDetailPoint createPointDetail(Order order, String phone, String action, Double actionPoint) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ODTp"+uuid.substring(0, 14);
        OrderDetailPoint table = new OrderDetailPoint();
        table.setOdtPointId(uuid);
        table.setPhoneNumber(phone);
        table.setOrder(order);
        table.setAction(action);
        table.setActionPoint(actionPoint);
        return orderDetailPointRepository.save(table);
    }
    /////////////////////////

    public OrderDetailPoint updatePointDetail(OrderDetailPoint orderDetailPoint) throws OptionException {
        /// verify
        if(Objects.isNull(orderDetailPoint)) throw OptionException.updateFail();
        /// save
        return orderDetailPointRepository.save(orderDetailPoint);
    }
    /////////////////////////

    public Optional<OrderDetailPoint> findById(String id){
        ///
        return orderDetailPointRepository.findById(id);
    }
    /////////////////////////

    public List<OrderDetailPoint> findAll() {
        ///
        return orderDetailPointRepository.findAll();
    }
    /////////////////////////

    public void deletePointDetail(String pointDetail) {
        ///
        orderDetailPointRepository.deleteById(pointDetail);
    }

    /////////////////////////


}
