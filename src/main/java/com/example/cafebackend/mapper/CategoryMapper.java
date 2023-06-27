package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForCategoryResponse;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.ProductForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    //ForCategoryResponse toForCategoryResponse(Category category, Integer prodCount);

    //ForCategoryResponse toForCategoryResponse(Category category, List<ProductForm> prods);

    @Mapping(target = "products", source = "productForm")
    ForCategoryResponse toForCategoryResponse(Category category);

    List<ForCategoryResponse> toListForCategoryResponse(List<Category> category);

}
