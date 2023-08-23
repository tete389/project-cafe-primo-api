package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;
import com.example.cafebackend.model.response.ForFindProdcut.*;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////
    /////// product base

    ForProductBaseMinPriceResponse toForProductBaseMinPriceResponse(ProductBase productBase, Double productMinPrice);

    ForProductBaseMinPriceFormResponse toForProductBaseMinPriceFormResponse(ProductBase productBase, Double productMinPrice);

    ForProductBaseResponse toForProductBaseResponse(ProductBase productBase);

    List<ForProductBaseResponse> toListForProductBaseResponse(List<ProductBase> productBaseList);


    ///// list





    ///////////////////////////////////////////////////////////////////////////
    /////// product form

//    ForProductFormResponse toForProductFormResponse(ProductForm productForm);
//
//    List<ForProductFormResponse> toListForProductFormResponse(List<ProductForm> productFormList);

    ForProductFormInfoResponse toForProductFormInfoResponse(ProductForm productForm);

    List<ForProductFormInfoResponse> toListForProductFormInfoResponse(List<ProductForm> productFormList);

    ForProductFormAddOnResponse toForProductFormAddOnResponse(ProductForm productForm);

    List<ForProductFormAddOnResponse> toListForProductFormAddOnResponse(List<ProductForm> productForm);

    ForProductFormInfoAddOnResponse toForProductFormInfoAddOnResponse(ProductForm productForm);

    ForProductFormAddOnOptionResponse toForProductFormAddOnOptionResponse(ProductForm productForm, List<ForAddOnResponse> addOnOption);

    ForProductFormInfoAddOnOptionResponse toForProductFormInfoAddOnOptionResponse(ProductForm productForm, List<ForAddOnResponse> addOnOption);


    //////////////////////////

}
