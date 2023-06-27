package com.example.cafebackend.mapper;


import com.example.cafebackend.model.response.OrderResponse;
import com.example.cafebackend.table.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    OrderResponse toOrderResponse(Order order);



}
