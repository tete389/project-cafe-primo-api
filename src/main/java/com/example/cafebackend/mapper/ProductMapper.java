package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForFindProdcut.*;
import com.example.cafebackend.model.response.ForFindMateUsed.ForFindMateUseInProdBaseResponse;
import com.example.cafebackend.model.response.ForFindMateUsed.ForFindMateUseInProdFormResponse;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    /////// product base
    ForProductBaseResponse toForProductBaseResponse(ProductBase productBase);

    List<ForProductBaseResponse> toListForProductBaseResponse(List<ProductBase> productBaseList);

    ForFindMateUseInProdBaseResponse toForFindMateUseInProdBaseResponse(ProductBase productBase, List<MaterialUsedNec> materialInfoUsed);

    ForProdBaseAndProdFormInfoResponse toForProdBaseAndProdFormInfoResponse(ProductBase productBase, List<ForFindMateEnableAndAddOnInPFResponse> productFormList);

    ForFindMateEnableInPBResponse toForFindMateEnableInPBResponse(ProductBase productBase);

    List<ForFindMateEnableInPBResponse> toListForFindMateEnableInPBResponse(List<ProductBase> productBaseList);

    /////// product form

    ForProductFormResponse toForProductFormResponse(ProductForm productForm);

    List<ForProductFormResponse> toListForProductFormResponse(List<ProductForm> productFormList);

    ForFindAddOnInPFResponse toForFindAddOnInPFResponse(ProductForm pd);

    ForFindMateEnableAndCateInPFResponse toForFindMateEnableAndCateInPFResponse(ProductForm pd);

    List<ForFindMateEnableAndCateInPFResponse> toListForFindMateEnableAndCateInPFResponse(List<ProductForm> productFormList);

    ForFindMateUseInProdFormResponse toForFindMateUseInProdFormResponse(ProductForm productForm, List<MaterialUsedNec> materialInfoUsed);

    ForFindMateEnableInPFResponse toForFindMateEnableInPFResponse(ProductForm productForm);

    List<ForFindMateEnableInPFResponse> toListForFindMateEnableInPFResponse(List<ProductForm> productFormList);


    ForFindMateEnableAndAddOnInPFResponse toForFindMateEnableAndAddOnInPFResponse(ProductForm productForm);

    List<ForFindMateEnableAndAddOnInPFResponse> toListForFindMateEnableAndAddOnInPFResponse(List<ProductForm> productFormList);


    //////////////////////////
    //ForProdAndListCategoryResponse toForProdAndListCategoryResponse (ProductForm pd);

//    ForProductOnlyResponse toProductFormOnlyResponse(ProductForm pd);
//
//    List<ForProductOnlyResponse> toListProductFormOnlyResponse(List<ProductForm> pd);


}
