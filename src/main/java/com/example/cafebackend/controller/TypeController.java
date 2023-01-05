package com.example.cafebackend.controller;

import com.example.cafebackend.model.request.ProdAddRequest;
import com.example.cafebackend.model.request.ProdTypeRequest;
import com.example.cafebackend.service.AdditionalService;
import com.example.cafebackend.service.ProductService;
import com.example.cafebackend.service.TypeService;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Product;
import com.example.cafebackend.table.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TypeController {

    private TypeService typeService;

    private ProductService productService;

    //////////////////////////////////////////////////////////////////////

//    public Type createType(Type request) {
//       return typeService.createType(request.getTypeName(), request.getTypeStatus(), request.getTypePrice());
//    }

    ////////////////////////////////////////

    public List<Type> getAllType(){
        return typeService.findAllAdd();
    }

    ////////////////////////////////////////

    public Type getTypeById(Type request){
        Optional<Type> type =  typeService.findById(request.getTypeId());
        return type.orElse(null);
    }

    ////////////////////////////////////////

    public Type updateType(Type request){
        Optional<Type> type =  typeService.findById(request.getTypeId());
        if(type.isEmpty()){
            return null; /// TODO
        }
        Type type1 = type.get();
        return typeService.updateType(type1, request.getTypeName(), request.getTypeStatus(), request.getTypePrice());
    }

    ////////////////////////////////////////

    public String deleteType(Type request){
        typeService.deleteTypeById(request.getTypeId());
       return "delete";
    }

    ////////////////////////////////////////

    public String addProductInType(ProdTypeRequest request){

        for (Integer i : request.getTypeId()){
            Optional<Type> type =  typeService.findById(i);
            if(type.isEmpty()){
                // TODO
                return "add1";
            }

//            List<Product> prodList = new ArrayList<>();
            for (Integer j : request.getProdId()){
                Optional<Product> prod = productService.findById(j);
                if(prod.isEmpty()){
                    // TODO
                    return "add2";
                }
//                prodList.add(prod.get());
                typeService.addProductInType(type.get(), prod.get());
            }
//            typeService.addProductInType(type.get(), prodList);
        }
        return "c";
    }

}
