package com.example.cafebackend.service;

import com.example.cafebackend.repository.MaterialRepository;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    //////////////////////////

    public Optional<Material> findById(Integer id){
        return materialRepository.findById(id);
    }

    /////////////////////////

    public List<Material> findAllMate() {
        return materialRepository.findAll();
    }

    /////////////////////////

    public Material createMaterial(String mateName, String mateStatus) {
        Material table = new Material();
        table.setMateName(mateName);
        table.setMateStatus(mateStatus);
        return materialRepository.save(table);
    }

    /////////////////////////

    public Material updateMaterial(Material mate, String mateName, String mateStatus) {
        mate.setMateName(mateName);
        mate.setMateStatus(mateStatus);
        return materialRepository.save(mate);
    }

    /////////////////////////

    public void deleteMaterial(Integer mateId) {
        materialRepository.deleteById(mateId);
    }

    /////////////////////////

    public void addProductInMate(Material mate, List<Product> prod){

        mate.setProduct(prod);
        materialRepository.save(mate);
    }

    /////////////////////////
    public void addMateInProd(Material mate, Product prod){

        mate.getProduct().add(prod);
        materialRepository.save(mate);
    }

}
