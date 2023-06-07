package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.OptionRecordRepository;
import com.example.cafebackend.table.OptionRecord;
import com.example.cafebackend.table.ProductRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OptionRecordService {

    private final OptionRecordRepository optionRecordRepository;

    public OptionRecordService(OptionRecordRepository optionRecordRepository) {
        this.optionRecordRepository = optionRecordRepository;
    }

    //////////////////////////

    public OptionRecord createOptionRecord(ProductRecord productRecord, String optionName, Double price) {
        OptionRecord table = new OptionRecord();
        table.setProductRecord(productRecord);
        table.setOption(optionName);
        table.setOptionPrice(price);
        return optionRecordRepository.save(table);
    }
    /////////////////////////

    public OptionRecord updateProdRecord(OptionRecord optionRecord) throws OptionException {
        /// verify
        if(Objects.isNull(optionRecord)) throw OptionException.updateFail();
        /// save
        return optionRecordRepository.save(optionRecord);
    }
    /////////////////////////

    public Optional<OptionRecord> findById(String id){
        ///
        return optionRecordRepository.findById(id);
    }
    /////////////////////////

    public List<OptionRecord> findAll() {
        ///
        return optionRecordRepository.findAll();
    }
    /////////////////////////

    public void clearProdRecord(OptionRecord productRecord) {
        ///
        productRecord.setProductRecord(null);
        optionRecordRepository.save(productRecord);
    }
    /////////////////////////

    public void deleteProdRecord(String orderDetailId) {
        ///
        optionRecordRepository.deleteById(orderDetailId);
    }

    /////////////////////////


}
