package com.example.cafebackend.mapper;


import com.example.cafebackend.model.response.ForFindCategory.ForCategoryProductBaseMinPriceResponse;
import com.example.cafebackend.model.response.ForFindCategory.ForCategoryResponse;
import com.example.cafebackend.model.response.ForFindProdcut.ForProductBaseMinPriceResponse;
import com.example.cafebackend.table.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////


    ForCategoryResponse toForCategoryResponse(Category category);

    List<ForCategoryResponse> toListForCategoryResponse(List<Category> categoryList);

    ForCategoryProductBaseMinPriceResponse toForCategoryProductInfoPriceResponse(Category category, List<ForProductBaseMinPriceResponse> productBasePrice);

//    @Mapping(target = "products", source = "productForm")
//    ForProdFormOfCategoryResponse toForProdFormOfCategoryResponse(Category category);
//
//    List<ForProdFormOfCategoryResponse> toListForProdFormOfCategoryResponse(List<Category> category);


//    @Mapping(target = "products", source = "productBase")
//    ForProdBaseOfCategoryResponse toForProdBaseOfCategoryResponse(Category category);
//
//    List<ForProdBaseOfCategoryResponse> toListForProdBaseOfCategoryResponse(List<Category> category);
//
//
//    ForCategoryAndProdFormInfoResponse toForCategoryAndProdInfoResponse(Category category, List<ForFindMateEnableInPFResponse> products);
//
//    ForCategoryAndProdBaseInfoResponse toForCategoryAndProdBaseInfoResponse(Category category, List<ForFindMateEnableInPBResponse> products);
//
//
//    ForCategoryAndProdPriceResponse toForCategoryAndProdPriceResponse(Category category, List<ForProductBaseInfoPriceResponse> products);

}
