package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.MaterialRepository;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Material;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    //////////////////////////

    public Material createMaterial(String mateName, Double mateStock) throws BaseException {
        /// verify
        if(materialRepository.existsByMateName(mateName)) throw MaterialException.createFailNameDuplicate();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        String n1 = String.valueOf(1000 + now.get(Calendar.SECOND) * now.get(Calendar.MINUTE));
        String n2 = String.valueOf(100 + now.get(Calendar.SECOND)+ now.get(Calendar.MINUTE));
        String Id = "M"+n1+n2;
        /// save
        Material table = new Material();
        table.setMateId(Id);
        table.setMateName(mateName);
        table.setIsEnable(true);
        table.setMateStock(mateStock);
        return materialRepository.save(table);
    }

    public Optional<Material> findById(String id){
        ///
        return materialRepository.findById(id);
    }

    public List<Material> findAllMate() {
        ///
        return materialRepository.findAll();
    }

    public Material updateMaterial(Material mate) throws BaseException {
        /// verify
        if(Objects.isNull(mate)) throw MaterialException.updateFail();
        /// save
        return materialRepository.save(mate);
    }


    /////////////////////////

    public Boolean deleteMaterial(String mateId) throws MaterialException {
        /// verify
        materialRepository.deleteById(mateId);
        Optional<Material> mate = materialRepository.findById(mateId);
        if(mate.isEmpty()) return true;
        throw MaterialException.deleteFail();
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