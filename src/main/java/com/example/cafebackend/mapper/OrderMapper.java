package com.example.cafebackend.mapper;


import com.example.cafebackend.model.response.ForOrder.OrderResponse;
import com.example.cafebackend.table.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////
    // @Mapping(target = "products", source = "productForm")
    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toListOrderResponse(List<Order> orderList);



}
