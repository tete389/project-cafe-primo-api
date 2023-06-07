//package com.example.cafebackend.service;
//
//import com.example.cafebackend.repository.AddOnRecordRepository;
//import com.example.cafebackend.table.AddOnRecord;
//import com.example.cafebackend.table.ProductRecord;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AddOnRecordService {
//
//    private final AddOnRecordRepository addOnRecordRepository;
//
//    public AddOnRecordService(AddOnRecordRepository addOnRecordRepository) {
//        this.addOnRecordRepository = addOnRecordRepository;
//    }
//
//
//    //////////////////////////
//
//    public Optional<AddOnRecord> findById(String id){
//        return addOnRecordRepository.findById(id);
//    }
//
//    /////////////////////////
//
//    public List<AddOnRecord> findAllAddDetail() {
//        return addOnRecordRepository.findAll();
//    }
//
//    /////////////////////////
//
//    public AddOnRecord createAdditionalDetail(ProductRecord productRecord, String addId, String addName, Double price, Integer addAmount, Double totalPrice) {
//        AddOnRecord table = new AddOnRecord();
//
//        table.setAddId(addId);
//        table.setAddName(addName);
//        table.setAddPrice(price);
//        table.setAddAmount(addAmount);
//        table.setAddDetailPrice(totalPrice);
//        table.setProductRecord(productRecord);
//       return addOnRecordRepository.save(table);
//    }
//
//    /////////////////////////
//
//    public AddOnRecord updateAdditionalDetail(AddOnRecord addDetail, String addName, Double price, Integer addAmount, Double totalPrice) {
//
//        addDetail.setAddName(addName);
//        addDetail.setAddAmount(addAmount);
//        addDetail.setAddPrice(price);
//        addDetail.setAddDetailPrice(totalPrice);
//        return addOnRecordRepository.save(addDetail);
//    }
//
//    /////////////////////////
//
//    public void deleteAdditionalDetail(String addDetailId) {
//        addOnRecordRepository.deleteById(addDetailId);
//    }
//
//    /////////////////////////
//
//
//}
