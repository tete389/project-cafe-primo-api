package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.repository.MaterialRepository;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.MaterialUsed;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    private final MaterialUsedService materialUsedService;

    public MaterialService(MaterialRepository materialRepository, MaterialUsedService materialUsedService) {
        this.materialRepository = materialRepository;
        this.materialUsedService = materialUsedService;

    }

    //////////////////////////

    public Material createMaterial(String mateName, Double mateStock, String unit) throws BaseException {
        /// verify
        if (materialRepository.existsByMateName(mateName))
            throw MaterialException.createFailNameDuplicate();

        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "M" + uuid.substring(0, 14);
        /// save
        Material table = new Material();
        table.setMateId(uuid);
        table.setMateName(mateName);
        table.setIsEnable(true);
        table.setMateUnit(unit);
        table.setStock(mateStock);
        table.setIsDelete(false);
        return materialRepository.save(table);
    }

    public Optional<Material> findById(String id) {
        ///
        return materialRepository.findMaterialById(id);
    }

    public List<Material> findAllMate() {
        ///
        return materialRepository.findMaterialALL();
    }

    public Integer findMateLowStock() {
        ///
        return materialRepository.findMaterialLowStock();
    }

    public List<Material> findAllMateASC() {
        ///
        return materialRepository.findMaterialALL();
    }

    public Material updateMaterial(Material mate) throws BaseException {
        /// verify
        if (Objects.isNull(mate))
            throw MaterialException.updateFail();
        /// save
        return materialRepository.save(mate);
    }

    public Boolean existsByName(String name) {
        ///
        return materialRepository.existsByMateName(name);
    }

    /////////////////////////

    public Boolean deleteMaterial(String mateId) throws BaseException {
        /// verify

        Optional<Material> material = materialRepository.findById(mateId);
        Material mateDel = material.get();
        List<MaterialUsed> listDeleteMateUse = new ArrayList<>();
        mateDel.getMaterialUsed().forEach(e -> listDeleteMateUse.add(e));
        for (MaterialUsed mateUse : listDeleteMateUse) {
            materialUsedService.deleteMaterialUsed(mateUse.getMateUsedId());
        }
        materialRepository.deleteById(mateId);
        Optional<Material> mate = materialRepository.findById(mateId);
        if (mate.isEmpty()) {
            return true;
        }
        throw MaterialException.deleteFail();

        // mateDel.setIsDelete(true);
        // mateDel.setIsEnable(false);

        // // material.get().();
        // materialRepository.save(mateDel);
        // return true;
    }

    /////////////////////////

    // public void addProductInMate(Material mate, List<Product> prod){
    //
    // mate.setProduct(prod);
    // materialRepository.save(mate);
    // }

    /////////////////////////
    // public void addMateInProd(Material mate, Product prod){
    //
    // mate.getProduct().add(prod);
    // materialRepository.save(mate);
    // }

}
