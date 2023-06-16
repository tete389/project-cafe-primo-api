//package com.example.cafebackend.service;
//
//import com.example.cafebackend.repository.IngredientRepository;
//import com.example.cafebackend.table.Ingredient;
//import com.example.cafebackend.table.IngredientKey;
//import com.example.cafebackend.table.Material;
//import com.example.cafebackend.table.Product;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class IngredientService {
//
//    private final IngredientRepository ingredientRepository;
//
//    public IngredientService(IngredientRepository ingredientRepository) {
//        this.ingredientRepository = ingredientRepository;
//    }
//
//    //////////////////////////
//
//    public Ingredient createIngredient(Product product, Material material, Double mateCount, String mateUnit, String description){
//        /// validate
//        if(description.isEmpty()) description = "none";
//        /// save
//        Ingredient table = new Ingredient();
//        table.setProduct(product);
//        table.setMaterial(material);
//        table.setMateCount(mateCount);
//        table.setMateUnit(mateUnit);
//        table.setDescription(description);
//        table.setIsEnable(true);
//        return ingredientRepository.save(table);
//    }
//
//    public Optional<Ingredient> findIngredientByIdKey(String mateId, String prodId) {
//        /// validate
//        IngredientKey idKey = new IngredientKey();
//        idKey.setMateId(mateId);
//        idKey.setProdId(prodId);
//        return ingredientRepository.findById(idKey);
//    }
//
//    public void delIngredient(IngredientKey id) {
//        ingredientRepository.deleteById(id);
//    }
//}
