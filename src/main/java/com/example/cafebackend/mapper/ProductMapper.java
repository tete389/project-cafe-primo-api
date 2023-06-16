package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.*;
import com.example.cafebackend.table.ProductForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////


    //@Mapping(target = "prod", source = "product_detail.product")
    ForProductOnlyResponse toProductFormOnlyResponse(ProductForm pd);

    List<ForProductOnlyResponse> toListProductFormOnlyResponse(List<ProductForm> pd);

    ForProdAndListAddOnResponse toForProdAndListAddOnResponse (ProductForm pd);

    //ForProdAndListIngResponse toFoProdAndListIngResponse (ProductForm pd);

    ForProdAndListCategoryResponse toForProdAndListCategoryResponse (ProductForm pd);

//    @Mapping(target = "prodImg", source = "image")
//    @Mapping(target = "point", source = "bonusPoint")
//    @Mapping(target = "isEnableMaterial", source = "ingredients.material.isEnable")
//    @Mapping(target = "des", source = "description")
//    ProductCustomerResponse toProductCustomerResponse(ProductForm productForm);
    ///////////////////////////////////////////////////////////////////////////


//    @Mapping(target = "baseProdId", source = "baseProduct.baseProdId")
//    @Mapping(target = "materials", source = "ingredients")
//    @Mapping(target = "categories", source = "category")
//    @Mapping(target = "addOn", source = "addOn")
//    ForProductInfoResponse toForProductInfoResponse (ProductForm pd);

    //List<ForProductInfoResponse> toListForProductInfoResponse(List<ProductForm> pd);

    ///////////////////////////////////////////////////////////////////////////
//    @Mapping(target = "additional", source = "productDetail.additional")
//    @Mapping(target = "sweetness", source = "productDetail.sweetness")
//    ProductCustomerResponse toProductCustomerResponse(ProductDetail productDetail);
//
//    ProductEmployeeResponse toProductEmployeeResponse(ProductDetail productDetail);
//
//    List<ProductDetailResponse> toListProductResponse(List<ProductDetail> productDetails);
//
//    List<ProductCustomerResponse> toListProductCustomerResponse(List<ProductDetail> productDetails);
//
//    List<ProductEmployeeResponse> toListProductEmployeeResponse(List<ProductDetail> productDetails);


}
