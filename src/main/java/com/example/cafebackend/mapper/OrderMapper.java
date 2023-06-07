package com.example.cafebackend.mapper;


import com.example.cafebackend.table.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    //OrderCustomerResponse toOrderCustomerResponse(Order order);

//    @Mapping(target = "productDetails", source = "order.productDetails")
//    OrderCustomerResponse toOrderCustomerResponse(OrderResponse order);


}
