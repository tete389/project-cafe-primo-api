package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.ProdAddRequest;
import com.example.cafebackend.model.request.ProdCateRequest;
import com.example.cafebackend.service.AdditionalService;
import com.example.cafebackend.service.CategoryService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdditionalController {

    private AdditionalService additionalService;

    private ProductService productService;

    //////////////////////////////////////////////////////////////////////

    public Additional createAdditional(Additional request) {
       return additionalService.createAdditional(request.getAddName(), request.getAddStatus(), request.getAddPrice());
    }

    ////////////////////////////////////////

    public List<Additional> getAllAdd(){
        return additionalService.findAllAdd();
    }

    ////////////////////////////////////////

    public Additional getAddById(Additional request){
        Optional<Additional> add =  additionalService.findById(request.getAddId());
        return add.orElse(null);
    }

    ////////////////////////////////////////

    public Additional updateAdd(Additional request){
        Optional<Additional> add =  additionalService.findById(request.getAddId());
        if(add.isEmpty()){
            return null; /// TODO
        }
        Additional additional = add.get();
        return additionalService.updateAdditional(additional, request.getAddName(), request.getAddStatus(), request.getAddPrice());
    }

    ////////////////////////////////////////

    public String deleteMate(Additional request){
        additionalService.deleteAdditional(request.getAddId());
        return "delete";
    }

    ////////////////////////////////////////

    public String addProductInAdd(ProdAddRequest request){

        for (Integer i : request.getAddId()){
            Optional<Additional> add =  additionalService.findById(i);
            if(add.isEmpty()){
                // TODO
                return "add1";
            }

            List<Product> prodList = new ArrayList<>();
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "add2";
                }
                prodList.add(prod.get());
            }
            additionalService.addProductInAdd(add.get(), prodList);
        }
        return "c";
    }

}
