package com.example.cafebackend.mapper;


import com.example.cafebackend.model.response.ForFindAddOnOpion.ForAddOnResponse;
import com.example.cafebackend.table.AddOn;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddOnMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    ForAddOnResponse toForAddOnResponse(AddOn addOn);

//    ForAddOnOptionInfoResponse toForFindMateUseInOptionResponse(AddOn addOn, List<ForOptionInfoResponse> optionsInfo);



    /////////////////// list

    List<ForAddOnResponse> toListForAddOnResponse(List<AddOn> addOnList);

    //List<ForAddOnOptionInfoResponse> toListForAddOnOptionInfoResponse(List<AddOn> addOnList);


    ////////////////////////////////////////  option

//    ForOptionInfoResponse toForOptionInfoResponse(Option option, List<Boolean> materialUsedEnable);

    /////////////////// list

//    List<ForOptionInfoResponse> toListForOptionInfoResponse(List<Option> optionList);
}
