package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForCategoryResponse;
import com.example.cafebackend.model.response.ForIngredientResponse;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Ingredient;
import com.example.cafebackend.table.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    //ForCategoryResponse toForCategoryResponse(Category category, Integer prodCount);

    @Mapping(target = "prodName", source = "product.prodName")
    @Mapping(target = "prodId", source = "product.prodId")
    @Mapping(target = "mateName", source = "material.mateName")
    @Mapping(target = "mateId", source = "material.mateId")
    ForIngredientResponse toForIngredientResponse(Ingredient ingredient);

}
