package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForFindCategory.ForCategoryAndProdBaseInfoResponse;
import com.example.cafebackend.model.response.ForFindCategory.ForCategoryAndProdFormInfoResponse;
import com.example.cafebackend.model.response.ForFindCategory.ForProdBaseOfCategoryResponse;
import com.example.cafebackend.model.response.ForFindCategory.ForProdFormOfCategoryResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForFindMateEnableInPBResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForFindMateEnableInPFResponse;
import com.example.cafebackend.table.Category;
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
    ForProdFormOfCategoryResponse toForProdFormOfCategoryResponse(Category category);

    List<ForProdFormOfCategoryResponse> toListForProdFormOfCategoryResponse(List<Category> category);


    @Mapping(target = "products", source = "productBase")
    ForProdBaseOfCategoryResponse toForProdBaseOfCategoryResponse(Category category);

    List<ForProdBaseOfCategoryResponse> toListForProdBaseOfCategoryResponse(List<Category> category);


    ForCategoryAndProdFormInfoResponse toForCategoryAndProdInfoResponse(Category category, List<ForFindMateEnableInPFResponse> products);

    ForCategoryAndProdBaseInfoResponse toForCategoryAndProdBaseInfoResponse(Category category, List<ForFindMateEnableInPBResponse> products);

}
