package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.repository.MaterialUsedRepository;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.MaterialUsed;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaterialUsedService {

    private final MaterialUsedRepository materialUsedRepository;

    public MaterialUsedService(MaterialUsedRepository materialUsedRepository) {
        this.materialUsedRepository = materialUsedRepository;
    }
    //////////////////////////

    public MaterialUsed updateMaterialUsed(MaterialUsed mateUsed) throws BaseException {
        /// verify
        if(Objects.isNull(mateUsed)) throw MaterialException.updateFail();
        /// save
        return materialUsedRepository.save(mateUsed);
    }

    public Optional<MaterialUsed> findById(String id){
        /// find
        return materialUsedRepository.findById(id);
    }

    public List<MaterialUsed> findAllMateUsed() {
        /// find
        return materialUsedRepository.findAll();
    }
    /////////////////////////

    public Optional<MaterialUsed> findByBaseIdAndMateId(String formId, String mateId) {
        /// find
        return materialUsedRepository.findByProdBaseIdAndMateId(formId, mateId);
    }
    /////////////////////////

    public Optional<MaterialUsed> findByFormIdAndMateId(String formId, String mateId) {
        /// find
        return materialUsedRepository.findByProdFormIdAndMateId(formId, mateId);
    }
    /////////////////////////

    public Optional<MaterialUsed> findByOptionIdAndMateId(String formId, String mateId) {
        /// find
        return materialUsedRepository.findByOptionIdAndMateId(formId, mateId);
    }
    /////////////////////////

    public void deleteMaterialUsedByFormId(String formId, String mateId) throws BaseException {
        /// verify
        if(Objects.isNull(formId)) throw MaterialException.updateFail();
        materialUsedRepository.deleteByProdFormId(formId, mateId);
    }
    /////////////////////////

    public void deleteMaterialUsed(String Id) throws BaseException {
        /// verify
        if(Objects.isNull(Id)) throw MaterialException.updateFail();
        materialUsedRepository.deleteById(Id);
        //materialUsedRepository.flush();
//        Optional<MaterialUsed> mate = materialUsedRepository.findById(Id);
//        if(mate.isEmpty()) return true;
//        throw MaterialException.deleteFail();
    }
    /////////////////////////

//    public void addProductInMate(Material mate, List<Product> prod){
//
//        mate.setProduct(prod);
//        materialRepository.save(mate);
//    }

    /////////////////////////
//    public void addMateInProd(Material mate, Product prod){
//
//        mate.getProduct().add(prod);
//        materialRepository.save(mate);
//    }

}
