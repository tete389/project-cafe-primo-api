package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForAddOnResponse;
import com.example.cafebackend.model.response.ForCategoryResponse;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Category;
import com.example.cafebackend.table.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddOnMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    //ForCategoryResponse toForCategoryResponse(Category category, Integer prodCount);

    ForAddOnResponse toForAddOnResponse(AddOn addOn, List<Product> prods);

}
