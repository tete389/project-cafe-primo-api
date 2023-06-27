package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.*;
import com.example.cafebackend.model.response.ForFind.ForFindAddOnInProdFormResponse;
import com.example.cafebackend.model.response.ForFind.ForFindMateUseInProdBaseResponse;
import com.example.cafebackend.model.response.ForFind.ForFindMateUseInProdFormResponse;
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




    /////// product form
    ForFindAddOnInProdFormResponse toForProdAndListAddOnResponse (ProductForm pd);

    ForProductInfoResponse toForProductInfoResponse (ProductForm pd);

    List<ForProductInfoResponse> toListForProductInfoResponse(List<ProductForm> productFormList);

    ForFindMateUseInProdBaseResponse toForFindMateUseInProdBaseResponse(ProductBase productBase, List<MaterialUsedNec> materialInfoUsed);

    ForFindMateUseInProdFormResponse toForFindMateUseInProdFormResponse(ProductForm productForm, List<MaterialUsedNec> materialInfoUsed);

    //////////////////////////
    //ForProdAndListCategoryResponse toForProdAndListCategoryResponse (ProductForm pd);

//    ForProductOnlyResponse toProductFormOnlyResponse(ProductForm pd);
//
//    List<ForProductOnlyResponse> toListProductFormOnlyResponse(List<ProductForm> pd);


}
