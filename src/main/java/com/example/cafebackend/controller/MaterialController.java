package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.ProdCateRequest;
import com.example.cafebackend.model.request.ProdMateRequest;
import com.example.cafebackend.service.CategoryService;
import com.example.cafebackend.service.MaterialService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Product;
import com.example.cafebackend.table.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaterialController {

    private MaterialService materialService;

    private ProductService productService;

    //////////////////////////////////////////////////////////////////////

    public Material createMaterial(Material request) {
       return materialService.createMaterial(request.getMateName(), request.getMateStatus());
    }

    ////////////////////////////////////////

    public List<Material> getAllMate(){
        return materialService.findAllMate();
    }

    ////////////////////////////////////////

    public Material getMateById(Material request){
        Optional<Material> mate =  materialService.findById(request.getMateId());
        return mate.orElse(null);
    }

    ////////////////////////////////////////

    public Material updateMate(Material request){
        Optional<Material> mate =  materialService.findById(request.getMateId());
        if(mate.isEmpty()){
            return null; /// TODO
        }
        Material material = mate.get();
        return materialService.updateMaterial(material, request.getMateName(), request.getMateStatus());
    }

    ////////////////////////////////////////

    public String deleteMate(Material request){
        materialService.deleteMaterial(request.getMateId());
        return "delete";
    }

    ////////////////////////////////////////

    public String addProductInMate(ProdMateRequest request){

        for (Integer i : request.getMateId()){
            Optional<Material> mate =  materialService.findById(i);
            if(mate.isEmpty()){
                // TODO
                return "mate1";
            }

            List<Product> prodList = new ArrayList<>();
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "mate2";
                }
                prodList.add(prod.get());
            }
            materialService.addProductInMate(mate.get(), prodList);
        }
        return "c";
    }

}
