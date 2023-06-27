package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.mapper.MaterialMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForMaterialResponse;
import com.example.cafebackend.model.response.ForMaterialUseResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.MaterialService;
import com.example.cafebackend.service.MaterialUsedService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.MaterialUsed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaterialController {

    private MaterialService materialService;

    private ProductFormService productFormService;

    private MaterialUsedService materialUsedService;

    private MaterialMapper materialMapper;

    private ProductMapper productMapper;

    //////////////////////////////////////////////////////////////////////

    public MessageResponse createMaterial(String mateName, Double mateStock, String unit) throws BaseException {
        /// validate
        if(Objects.isNull(mateName) || mateName.isEmpty()) throw MaterialException.createFailRequestNull();
        if(Objects.isNull(mateStock)) throw MaterialException.createFailRequestNull();
        /// verify
        Material mate = materialService.createMaterial(mateName, mateStock, unit);
        MessageResponse res = new MessageResponse();
        res.setMessage("create Material success");
        res.setRes(mate);
        return res;

    }
    ////////////////////////////////////////

    public MessageResponse updateMaterial(String mateId, String mateName, String mateStock, String isEnable, String unit) throws BaseException {
        /// validate
        if(Objects.isNull(mateId) || mateId.isEmpty()) throw MaterialException.findFailRequestNull();
        if(Objects.isNull(mateName) || mateName.isEmpty()) throw MaterialException.findFailRequestNull();
        if(Objects.isNull(mateStock) || mateStock.isEmpty()) throw MaterialException.findFailRequestNull();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw MaterialException.findFailRequestNull();
        /// verify
        Optional<Material> mate =  materialService.findById(mateId);
        if(mate.isEmpty()) throw MaterialException.findFail();
        Material material = mate.get();
        /// check mate name
        if (!mateName.equals(material.getMateName())) {
            if (materialService.existsByName(mateName)) throw MaterialException.updateFail();
            material.setMateName(mateName);
        }
        /// check stock
        Double stock = Double.valueOf(mateStock);
        if (!stock.equals(material.getStock())) {
            material.setStock(stock);
        }
        /// check isEnable
        String enable = String.valueOf(material.getIsEnable());
        if (!isEnable.equals(enable)) {
            material.setIsEnable(Boolean.valueOf(isEnable));
            for (MaterialUsed mateUse : material.getMaterialUsed()) {
                Optional<MaterialUsed> opt =  materialUsedService.findById(mateUse.getMateUsedId());
                if(opt.isEmpty()) throw MaterialException.findFail();
                opt.get().setIsEnable(Boolean.valueOf(isEnable));
            }
        }
        /// check unit
        if (!unit.equals(material.getMateUnit())) {
            material.setMateUnit(unit);
        }
        ///
        Material mateRes = materialService.updateMaterial(material);
        MessageResponse res = new MessageResponse();
        res.setMessage("update Material success");
        res.setRes(mateRes);
        return res;
    }

    ////////////////////////////////////////

    public MessageResponse getMaterialById(String mateId) throws BaseException {
        /// validate
        if(Objects.isNull(mateId) || mateId.isEmpty()) throw MaterialException.findFailRequestNull();
        /// verify
        Optional<Material> mate =  materialService.findById(mateId);
        if(mate.isEmpty()) throw MaterialException.findFail();
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Material By ID");
        res.setRes(mate);
        return res;
    }


    public MessageResponse getMaterialAll(){
        List<Material> mateList = materialService.findAllMate();
//        List<ForMaterialResponse> mateResList = new ArrayList<>();
//        for (Material mate : mateList){
//            List<ProductForm> prodList = productFormService.findProductByMaterialId(mate.getMateId());
//            ForMaterialResponse mateRes = materialMapper.toForMaterialResponse(mate, prodList);
//            mateResList.add(mateRes);
//        }
        //List<ForMaterialResponse> mateRes = materialMapper.toListForMaterialResponse(materials);
        /// res
        MessageResponse res = new MessageResponse();
        res.setMessage("get Material All");
        res.setRes(mateList);
        return res;
    }

    public MessageResponse findListMateUseByMateId(String mateId) throws BaseException {
        /// validate
        if(Objects.isNull(mateId) || mateId.isEmpty())throw MaterialException.findFail();
        /// verify
        Optional<Material> material = materialService.findById(mateId);
        if(material.isEmpty()) throw MaterialException.findFail();
        Material mate = material.get();

        List<ForMaterialUseResponse> mateUseMap = materialMapper.toListForMaterialUseResponse(mate.getMaterialUsed());
        ForMaterialResponse mateRes = materialMapper.toForMaterialResponse(mate, mateUseMap);
        MessageResponse res = new MessageResponse();
        res.setMessage("get List MaterialUsed By Material ID");
        res.setRes(mateRes);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse deleteMate(String mateId) throws MaterialException {
        Boolean mate =  materialService.deleteMaterial(mateId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Material success");
        res.setRes(mate);
        return res;
    }
    ////////////////////////////////////////


}
