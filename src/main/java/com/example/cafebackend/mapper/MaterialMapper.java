package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.model.response.ForMaterialResponse;
import com.example.cafebackend.model.response.ForMaterialUseResponse;
import com.example.cafebackend.table.Material;
import com.example.cafebackend.table.MaterialUsed;
import com.example.cafebackend.table.ProductForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    ///////////////////// mate

    //ForMaterialResponse toForMaterialResponse(Material material, List<ProductForm> prods);

    ForMaterialResponse toForMaterialResponse(Material material, List<ForMaterialUseResponse> materialUses);


    ///////////////////// mate use

    @Mapping(target = "productForms", source = "productForm")
    @Mapping(target = "productBases", source = "productBase")
    @Mapping(target = "options", source = "option")
    ForMaterialUseResponse toForMaterialUseResponse(MaterialUsed materialUsed);

    List<ForMaterialUseResponse> toListForMaterialUseResponse(List<MaterialUsed> materialUsed);


    MaterialUsedNec toMaterialUsedNec(MaterialUsed materialUsed, Material material);

}
