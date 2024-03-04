package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.repository.MaterialUsedRepository;
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
        if (Objects.isNull(mateUsed))
            throw MaterialException.updateFail();
        /// save
        return materialUsedRepository.save(mateUsed);
    }

    public Optional<MaterialUsed> findById(String id) {
        /// find
        return materialUsedRepository.findById(id);
    }

    public List<MaterialUsed> findAllMateUsed() {
        /// find
        return materialUsedRepository.findAll();
    }
    /////////////////////////

    public Optional<MaterialUsed> findByBaseIdAndMateId(String baseId, String mateId) {
        /// find
        return materialUsedRepository.findByProdBaseIdAndMateId(baseId, mateId);
    }

    public Optional<MaterialUsed> findByFormIdAndMateId(Long formId, String mateId) {
        /// find
        return materialUsedRepository.findByProdFormIdAndMateId(formId, mateId);
    }

    public Optional<MaterialUsed> findByOptionIdAndMateId(String optionId, String mateId) {
        /// find
        return materialUsedRepository.findByOptionIdAndMateId(optionId, mateId);
    }
    /////////////////////////

    public List<Boolean> findEnableByBaseId(String baseId) {
        /// find
        return materialUsedRepository.findByProdBaseId(baseId);
    }

    public List<Boolean> findEnableByFormId(String formId) {
        /// find
        return materialUsedRepository.findByProdFormId(formId);
    }

    public List<Boolean> findEnableByOptionId(String opId) {
        /// find
        return materialUsedRepository.findByOptionId(opId);
    }
    /////////////////////////

    public void deleteMaterialUsed(String Id) throws BaseException {
        /// verify
        // try {
        if (Objects.isNull(Id) || Id.isEmpty())
            throw MaterialException.updateFail();
        materialUsedRepository.deleteById(Id);
        materialUsedRepository.flush();
        // } catch (Exception e) {
        // throw new Exception(e);
        // }
        // Optional<MaterialUsed> mate = materialUsedRepository.findById(Id);
        // if(mate.isEmpty()) return true;
        // throw MaterialException.deleteFail();
    }
    /////////////////////////

}
