package com.example.cafebackend.service;

import com.example.cafebackend.repository.AdditionalRepository;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalService {

    private final AdditionalRepository additionalRepository;

    public AdditionalService(AdditionalRepository additionalRepository) {
        this.additionalRepository = additionalRepository;
    }


    //////////////////////////

    public Optional<Additional> findById(Integer id){
        return additionalRepository.findById(id);
    }

    /////////////////////////

    public List<Additional> findAllAdd() {
        return additionalRepository.findAll();
    }

    /////////////////////////

    public Additional createAdditional(String addName, String addStatus, double addPrice) {
        Additional table = new Additional();
        table.setAddName(addName);
        table.setAddStatus(addStatus);
        table.setAddPrice(addPrice);
        return additionalRepository.save(table);
    }

    /////////////////////////

    public Additional updateAdditional(Additional add, String addName, String addStatus, double addPrice) {
        add.setAddName(addName);
        add.setAddStatus(addStatus);
        add.setAddPrice(addPrice);
        return additionalRepository.save(add);
    }

    /////////////////////////

    public void deleteAdditional(Integer addId){
        additionalRepository.deleteById(addId);
    }

    /////////////////////////

    public void addProductInAdd(Additional add, List<Product> prod){

        add.getProduct().addAll(prod);
        additionalRepository.save(add);
    }

    /////////////////////////
    public void addAddInProd(Additional add, Product prod){

        add.getProduct().add(prod);
        additionalRepository.save(add);
    }

}
