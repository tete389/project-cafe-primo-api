package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.AddDetailRequest;
import com.example.cafebackend.service.AdditionalDetailService;
import com.example.cafebackend.service.AdditionalService;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.AdditionalDetail;
import com.example.cafebackend.table.OrderDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdditionalDetailController {

    private AdditionalDetailService additionalDetailService;

    private AdditionalService additionalService;


    ////////////////////////////////////////////////

    public List<AdditionalDetail> getAllAdditionalDetail(){
        return additionalDetailService.findAllAddDetail();
    }

    ////////////////////////////////////////////////

    public Optional<AdditionalDetail> getByOrderId(Integer id){
        return additionalDetailService.findById(id);
    }

    ////////////////////////////////////////////////

    public AdditionalDetail createAdditionalDetail(OrderDetail orderDetail, AddDetailRequest request) {
        Optional<Additional> add =  additionalService.findById(request.getAddId());
        if(add.isEmpty()){
            /// TODO
            return null;
        }
        Additional additional = add.get();
        Double prices = additional.getAddPrice() * request.getAddAmount();
        return additionalDetailService.createAdditionalDetail(orderDetail, request.getAddAmount(), prices , additional);
    }

    ////////////////////////////////////////



    ////////////////////////////////////////

    public Optional<AdditionalDetail> getAddDetById(AdditionalDetail request){
         return additionalDetailService.findById(request.getAddDetailId());

    }

    ////////////////////////////////////////


}
