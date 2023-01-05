package com.example.cafebackend.service;

import com.example.cafebackend.repository.TypeRepository;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Product;
import com.example.cafebackend.table.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    //////////////////////////

    public Optional<Type> findById(Integer id){
        return typeRepository.findById(id);
    }

    /////////////////////////

    public List<Type> findAllAdd() {
        return typeRepository.findAll();
    }

    /////////////////////////

    public Type createType(String typeName, String typeStatus, double typePrice, Product product) {
        Type table = new Type();
        table.setTypeName(typeName);
        table.setTypeStatus(typeStatus);
        table.setTypePrice(typePrice);
        table.setProduct(product);
        return typeRepository.save(table);
    }

    /////////////////////////

    public Type updateType(Type type, String typeName, String typeStatus, double typePrice) {
        type.setTypeName(typeName);
        type.setTypeStatus(typeStatus);
        type.setTypePrice(typePrice);
        return typeRepository.save(type);
    }

    /////////////////////////

    public void deleteTypeById(Integer typeId) {
        typeRepository.deleteById(typeId);
    }

    /////////////////////////
    public void addProductInType(Type type, Product prod){

        type.setProduct(prod);
        typeRepository.save(type);
    }

    /////////////////////////
    public void addTypeInProd(Type type, Product prod){

        type.setProduct(prod);
        typeRepository.save(type);
    }

}
