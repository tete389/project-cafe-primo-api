package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForCategoryResponse;
import com.example.cafebackend.model.response.ForMaterialResponse;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    //ForCategoryResponse toForCategoryResponse(Category category, Integer prodCount);

    //@Mapping(target = "ingredient", source = "")
    ForMaterialResponse toForMaterialResponse(Material material, List<Product> prods);



}
