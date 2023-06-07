//package com.example.cafebackend.controller;
//
//import com.example.cafebackend.exception.BaseException;
//import com.example.cafebackend.exception.OrderException;
//import com.example.cafebackend.model.request.AddDetailRequest;
//import com.example.cafebackend.service.AddOnRecordService;
//import com.example.cafebackend.service.AddOnService;
//import com.example.cafebackend.table.AddOnRecord;
//import com.example.cafebackend.table.Option;
//import com.example.cafebackend.table.ProductRecord;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@AllArgsConstructor
//@Service
//public class AddOnRecordController {
//
//    private AddOnRecordService addOnRecordService;
//
//    private AddOnService addOnService;
//
//
//    ////////////////////////////////////////////////
//
//    public List<AddOnRecord> getAllAdditionalDetail(){
//        return addOnRecordService.findAllAddDetail();
//    }
//
//    ////////////////////////////////////////////////
//
//    public Optional<AddOnRecord> getByOrderId(String id){
//        return addOnRecordService.findById(id);
//    }
//
//    ////////////////////////////////////////////////
//
//    public AddOnRecord createAdditionalDetail(ProductRecord productRecord, AddDetailRequest request) throws BaseException {
//        Optional<Option> add =  addOnService.findById(request.getAddId());
//        if(add.isEmpty()){
//            throw OrderException.createFail();
//        }
//        int addAmount = request.getAddAmount();
//        if(addAmount == 0){
//            addAmount = 1;
//        }
//        Option option = add.get();
//        double prices = option.getAddPrice();
//        double totalPrice = prices * addAmount;
//        return addOnRecordService.createAdditionalDetail(productRecord, option.getAddId(), option.getAddName(), prices, addAmount, totalPrice);
//    }
//
//    ////////////////////////////////////////
//
//
//
//    ////////////////////////////////////////
//
//    public Optional<AddOnRecord> getAddDetById(AddOnRecord request){
//         return addOnRecordService.findById(request.getAddDetailId());
//
//    }
//
//    ////////////////////////////////////////
//
//
//}
