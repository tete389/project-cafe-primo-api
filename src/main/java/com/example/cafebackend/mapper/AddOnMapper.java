package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ForAddOnResponse;
import com.example.cafebackend.model.response.ForFindMateUsed.ForFindMateUseInOptionResponse;
import com.example.cafebackend.model.response.ForFindNecessary.MaterialUsedNec;
import com.example.cafebackend.table.AddOn;
import com.example.cafebackend.table.Option;
import com.example.cafebackend.table.ProductForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddOnMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    //ForCategoryResponse toForCategoryResponse(Category category, Integer prodCount);

    ForAddOnResponse toForAddOnResponse(AddOn addOn);

    List<ForAddOnResponse> toListForAddOnResponse(List<AddOn> addOn);


    ForFindMateUseInOptionResponse toForFindMateUseInOptionResponse(Option option, List<MaterialUsedNec> materialInfoUsed);

}
