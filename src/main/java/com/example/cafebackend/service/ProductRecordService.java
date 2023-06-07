package com.example.cafebackend.service;

import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.ProductRecordRepository;
import com.example.cafebackend.table.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductRecordService {

    private final ProductRecordRepository productRecordRepository;

    public ProductRecordService(ProductRecordRepository productRecordRepository) {
        this.productRecordRepository = productRecordRepository;
    }
    //////////////////////////

    public Optional<ProductRecord> findById(String id){
        ///
        return productRecordRepository.findById(id);
    }
    /////////////////////////

    public List<ProductRecord> findByOrderId(String id){
        ///
        return productRecordRepository.findAllByOrderOrderId(id);
    }
    /////////////////////////

    public List<ProductRecord> findAllDetail() {
        ///
        return productRecordRepository.findAll();
    }
    /////////////////////////

    public ProductRecord createProdRecord(Order order) {
        ProductRecord table = new ProductRecord();
        /// save order
        table.setOrder(order);
        return productRecordRepository.save(table);
    }
    /////////////////////////

    public ProductRecord updateProdRecord(ProductRecord productRecord) throws OrderException {
        /// verify
        if(Objects.isNull(productRecord)) throw OrderException.updateFailDataNull();
        /// save prod record to database
        return productRecordRepository.save(productRecord);
    }
    /////////////////////////

    public void clearProdRecord(ProductRecord productRecord) {
        ///
        productRecord.setOrder(null);
        productRecordRepository.save(productRecord);
    }
    /////////////////////////

    public void deleteProdRecord(String orderDetailId) {
        ///
        productRecordRepository.deleteById(orderDetailId);
    }
    /////////////////////////

}
