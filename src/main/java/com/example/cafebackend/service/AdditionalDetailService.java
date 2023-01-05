package com.example.cafebackend.service;

import com.example.cafebackend.repository.AdditionalDetailRepository;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.AdditionalDetail;
import com.example.cafebackend.table.OrderDetail;
import com.example.cafebackend.table.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalDetailService {

    private final AdditionalDetailRepository additionalDetailRepository;

    public AdditionalDetailService(AdditionalDetailRepository additionalDetailRepository) {
        this.additionalDetailRepository = additionalDetailRepository;
    }


    //////////////////////////

    public Optional<AdditionalDetail> findById(Integer id){
        return additionalDetailRepository.findById(id);
    }

    /////////////////////////

    public List<AdditionalDetail> findAllAddDetail() {
        return additionalDetailRepository.findAll();
    }

    /////////////////////////

    public AdditionalDetail createAdditionalDetail(OrderDetail orderDetail, Integer addAmount, Double addPriceS, Additional additional) {
        AdditionalDetail table = new AdditionalDetail();
        table.setAddAmount(addAmount);
        table.setAddPriceS(addPriceS);
        table.setAdditional(additional);
        table.setOrderDetail(orderDetail);
       return additionalDetailRepository.save(table);
    }

    /////////////////////////

    public AdditionalDetail updateAdditionalDetail(AdditionalDetail addDetail, Integer addAmount, Double addPriceS, Additional additional) {
        addDetail.setAddAmount(addAmount);
        addDetail.setAddPriceS(addPriceS);
        addDetail.setAdditional(additional);
        return additionalDetailRepository.save(addDetail);
    }

    /////////////////////////

    public void deleteAdditionalDetail(Integer addDetailId) {
        additionalDetailRepository.deleteById(addDetailId);
    }

    /////////////////////////


}
