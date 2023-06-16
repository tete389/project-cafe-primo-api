package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.model.request.ProdRequest;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductRecordController {

    private OrderService orderService;

    private ProductRecordService productRecordService;

    private OptionRecordService optionRecordService;

    private ProductFormService productFormService;

    private OptionService optionService;


    ////////////////////////////////////////////////

    public List<ProductRecord> getAddProdRecord(){
        ///
        return productRecordService.findAllDetail();
    }
    ////////////////////////////////////////////////

    public Optional<ProductRecord> getByOrderId(String id){
        ///
        return productRecordService.findById(id);
    }
    ////////////////////////////////////////////////

    public ProductRecord createProductRecord(Order order, ProdRequest request) throws BaseException {
        /// validate product
        Optional<ProductForm> product = productFormService.findProductFormById(request.getProdId());
        if(product.isEmpty()) throw OrderException.createFail();
        ProductForm prod = product.get();
        /// add order
        ProductRecord productRecord = productRecordService.createProdRecord(order);
        /// price
        double prices = prod.getPrice();
        /// check options
        if(!request.getOptions().isEmpty()){
            for(Option optionRequest : request.getOptions()){
                Optional<Option> option = optionService.findOptionById(optionRequest.getOptionId());
                if(option.isEmpty()) throw OptionException.findFail();
                Option opt = option.get();
                /// create option record and add prod record
                OptionRecord optionRecord = optionRecordService.createOptionRecord(productRecord, opt.getOptionName(), opt.getPrice());
                /// check options price
                prices = prices + opt.getPrice();
                /// add option record
                productRecord.getOptionRecords().add(optionRecord);
            }
        }
        /// check amount
        int prodAmount =  request.getAmount();
        if(prodAmount == 0) prodAmount = 1;
        /// check price
        double totalPrice = prices * prodAmount;
        /// check bonus point
        double bonusPoint = prod.getBonusPoint() * prodAmount;
        /// add data
        //productRecord.setProdName(prod.getProdForm());
        productRecord.setProdPrice(prod.getPrice());
        productRecord.setProdAmount(prodAmount);
        productRecord.setPrice(prices);
        productRecord.setTotalPrice(totalPrice);
        productRecord.setBonusPoint(bonusPoint);
        productRecord.setProdId(prod.getProdFormId());

        /// save
        return productRecordService.updateProdRecord(productRecord);
    }
    ////////////////////////////////////////////////////

    public boolean clearProductRecordInOrder(String orderId) throws BaseException {
        /// validate
        List<ProductRecord> productRecord = productRecordService.findByOrderId(orderId);
        if(productRecord.isEmpty()) return true;
        ///
        for (ProductRecord prodRec : productRecord) {
            //productRecordService.clearProdRecord(prodRec);
            productRecordService.deleteProdRecord(prodRec.getProdRecId());
            Optional<ProductRecord> deleteProdRec = productRecordService.findById(prodRec.getProdRecId());
            if (!(Objects.isNull(deleteProdRec) || deleteProdRec.isEmpty())) return false;
        }
        return true;
    }
}
