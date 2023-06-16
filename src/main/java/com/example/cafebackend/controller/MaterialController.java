package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.MaterialException;
import com.example.cafebackend.mapper.MaterialMapper;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.ForMaterialResponse;
import com.example.cafebackend.model.response.ForProductOnlyResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.service.MaterialService;
import com.example.cafebackend.service.ProductFormService;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.ProductForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaterialController {

    private MaterialService materialService;

    private ProductFormService productFormService;

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

    public MessageResponse updateMaterial(String mateId, String mateName, String mateStock, String isEnable) throws BaseException {
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
        if (!mateName.equals(material.getMateName())) {
            if (materialService.existsByName(mateName)) throw MaterialException.updateFail();
            material.setMateName(mateName);
        }
        mate.get().setMateName(mateName);
        Material mateRes = materialService.updateMaterial(mate.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update Name Material complete");
        res.setRes(mateRes);
        return res;
    }

    public MessageResponse setMaterialStock(String mateId, Double mateStock) throws BaseException {
        /// validate
        if(Objects.isNull(mateId) || mateId.isEmpty()) throw MaterialException.findFailRequestNull();
        if(Objects.isNull(mateStock)) throw MaterialException.findFailRequestNull();
        /// verify
        Optional<Material> mate =  materialService.findById(mateId);
        if(mate.isEmpty()) throw MaterialException.findFail();
        mate.get().setStock(mateStock);
        Material mateRes = materialService.updateMaterial(mate.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update Stock Material complete");
        res.setRes(mateRes);
        return res;
    }

    public MessageResponse setMaterialEnable(String mateId, Boolean enable) throws BaseException {
        /// validate
        if(Objects.isNull(mateId) || mateId.isEmpty()) throw MaterialException.findFailRequestNull();
        if(Objects.isNull(enable)) throw MaterialException.findFailRequestNull();
        /// verify
        Optional<Material> mate =  materialService.findById(mateId);
        if(mate.isEmpty()) throw MaterialException.findFail();
        mate.get().setIsEnable(enable);
        Material mateRes = materialService.updateMaterial(mate.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("update Enable complete");
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
        MessageResponse res = new MessageResponse();
        res.setMessage("get Material By ID complete");
        res.setRes(mate);
        return res;
    }


    public MessageResponse getMaterialAll(){
        List<Material> mateList = materialService.findAllMate();
        List<ForMaterialResponse> mateResList = new ArrayList<>();
        for (Material mate : mateList){
            List<ProductForm> prodList = productFormService.findProductByMaterialId(mate.getMateId());
            ForMaterialResponse mateRes = materialMapper.toForMaterialResponse(mate, prodList);
            mateResList.add(mateRes);
        }
        //List<ForMaterialResponse> mateRes = materialMapper.toListForMaterialResponse(materials);
        MessageResponse res = new MessageResponse();
        res.setMessage("get Material All complete");
        res.setRes(mateResList);
        return res;
    }

    public MessageResponse findListProductByMaterialId(String mateId) throws BaseException {
        /// validate
        if(Objects.isNull(mateId) || mateId.isEmpty())throw MaterialException.findFail();
        /// verify
        Optional<Material> material = materialService.findById(mateId);
        if(material.isEmpty()) throw MaterialException.findFail();
        List<ProductForm> productForms =  productFormService.findProductByMaterialId(mateId);
        List<ForProductOnlyResponse> pdList = productMapper.toListProductFormOnlyResponse(productForms);
        MessageResponse res = new MessageResponse();
        res.setMessage("get ListProducts By Material ID");
        res.setRes(pdList);
        return res;
    }
    ////////////////////////////////////////

    public MessageResponse deleteMate(String mateId) throws MaterialException {
        Boolean mate =  materialService.deleteMaterial(mateId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Material complete");
        res.setRes(mate);
        return res;
    }
    ////////////////////////////////////////


}
