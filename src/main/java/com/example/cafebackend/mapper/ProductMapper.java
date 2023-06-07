package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.*;
import com.example.cafebackend.table.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    @Mapping(target = "baseProdId", source = "baseProduct.baseProdId")
    //@Mapping(target = "prod", source = "product_detail.product")
    ForProductOnlyResponse toProductOnlyResponse(Product pd);

    List<ForProductOnlyResponse> toListProductOnlyResponse(List<Product> pd);

    ForProdAndListAddOnResponse toForProdAndListAddOnResponse (Product pd);

    ForProdAndListIngResponse toFoProdAndListIngResponse (Product pd);

    ForProdAndListCategoryResponse toForProdAndListCategoryResponse (Product pd);
    ///////////////////////////////////////////////////////////////////////////


    @Mapping(target = "baseProdId", source = "baseProduct.baseProdId")
    @Mapping(target = "materials", source = "ingredients")
    @Mapping(target = "categories", source = "category")
    @Mapping(target = "addOn", source = "addOn")
    ForProductInfoResponse toForProductInfoResponse (Product pd);

    List<ForProductInfoResponse> toListForProductInfoResponse(List<Product> pd);

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
