package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForFindMateUsed.ForMaterialUseOfBaseResponse;
import com.example.cafebackend.model.response.ForFindMateUsed.ForMaterialUseOfFormResponse;
import com.example.cafebackend.model.response.ForFindMateUsed.ForMaterialUseOfOptionResponse;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.model.response.ForFindMateUsed.ForMaterialUseResponse;
import com.example.cafebackend.table.*;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MaterialMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    ///////////////////// mate

    //ForMaterialResponse toForMaterialResponse(Material material, List<ProductForm> prods);

    //ForMaterialResponse toForMaterialResponse(Material material, List<ForMaterialUseResponse> materialUses);


    ///////////////////// mate use

    ForMaterialUseOfBaseResponse toForMaterialUseOfBaseResponse(MaterialUsed materialUsed, ProductBase productBaseRes);

    ForMaterialUseOfFormResponse toForMaterialUseOfFormResponse(MaterialUsed materialUsed, ProductForm productFormRes);

    ForMaterialUseOfOptionResponse toForMaterialUseOfOptionResponse(MaterialUsed materialUsed, Option optionRes);

//    List<ForMaterialUseOfBaseResponse> toListForMaterialUseOfBaseResponse(List<MaterialUsed> materialUsed);

    ForMaterialUseResponse toForMaterialUseResponse(MaterialUsed materialUsed);

//    List<ForMaterialUseResponse> toListForMaterialUseResponse(List<MaterialUsed> materialUsed);


    MaterialUsedNec toMaterialUsedNec(MaterialUsed materialUsed, Material material);

}
