package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.repository.PointDetailRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PointDetailService {

    private final PointDetailRepository pointDetailRepository;

    public PointDetailService(PointDetailRepository pointDetailRepository) {
        this.pointDetailRepository = pointDetailRepository;
    }

    //////////////////////////

    public PointDetail createPointDetail(Customer customer, Order order, Double point, String status) {
        PointDetail table = new PointDetail();
        table.setCustomer(customer);
        table.setOrder(order);
        table.setPoint(point);
        table.setStatus(status);
        return pointDetailRepository.save(table);
    }
    /////////////////////////

    public PointDetail updatePointDetail(PointDetail pointDetail) throws OptionException {
        /// verify
        if(Objects.isNull(pointDetail)) throw OptionException.updateFail();
        /// save
        return pointDetailRepository.save(pointDetail);
    }
    /////////////////////////

    public Optional<PointDetail> findById(String id){
        ///
        return pointDetailRepository.findById(id);
    }
    /////////////////////////

    public List<PointDetail> findAll() {
        ///
        return pointDetailRepository.findAll();
    }
    /////////////////////////

    public void deletePointDetail(String pointDetail) {
        ///
        pointDetailRepository.deleteById(pointDetail);
    }

    /////////////////////////


}
