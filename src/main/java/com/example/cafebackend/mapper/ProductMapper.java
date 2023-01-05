package com.example.cafebackend.mapper;

import com.example.cafebackend.model.response.ProductCustomerResponse;
import com.example.cafebackend.model.response.ProductResponse;
import com.example.cafebackend.table.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ///////////////////////////////////////////////////////////////////////////

    ProductResponse toProductResponse(Product product);

    ProductCustomerResponse toProductCustomerResponse(Product product);

    List<ProductResponse> toListProductResponse(List<Product> products);

    List<ProductCustomerResponse> toListProductCustomerResponse(List<Product> products);



}
