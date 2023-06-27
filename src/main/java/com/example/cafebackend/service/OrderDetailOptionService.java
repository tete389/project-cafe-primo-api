package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.repository.OrderDetailOptionRepository;
import com.example.cafebackend.table.OrderDetailOption;
import com.example.cafebackend.table.OrderDetailProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderDetailOptionService {

    private final OrderDetailOptionRepository orderDetailOptionRepository;

    public OrderDetailOptionService(OrderDetailOptionRepository orderDetailOptionRepository) {
        this.orderDetailOptionRepository = orderDetailOptionRepository;
    }

    //////////////////////////

    public OrderDetailOption createOrderDetailOption(OrderDetailProduct orderDetailProduct, String optionName, Double price) {
        OrderDetailOption table = new OrderDetailOption();
        table.setOrderDetailProduct(orderDetailProduct);
        table.setOptionName(optionName);
        table.setOptionPrice(price);
        return orderDetailOptionRepository.save(table);
    }
    /////////////////////////

    public OrderDetailOption updateOrderDetailOption(OrderDetailOption orderDetailOption) throws OptionException {
        /// verify
        if(Objects.isNull(orderDetailOption)) throw OptionException.updateFail();
        /// save
        return orderDetailOptionRepository.save(orderDetailOption);
    }
    /////////////////////////

    public Optional<OrderDetailOption> findById(String id){
        ///
        return orderDetailOptionRepository.findById(id);
    }
    /////////////////////////

    public List<OrderDetailOption> findAll() {
        ///
        return orderDetailOptionRepository.findAll();
    }
    /////////////////////////

    public List<OrderDetailOption> findByOrderDetailOptionBetweenDate(String start, String end, String status) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionBetweenDate(start, end, status);
    }
    /////////////////////////

    public void clearOrderDetailOption(OrderDetailOption option) {
        ///
        option.setOrderDetailProduct(null);
        orderDetailOptionRepository.save(option);
    }
    /////////////////////////

    public void deleteOrderDetailOption(String option) {
        ///
        orderDetailOptionRepository.deleteById(option);
    }

    /////////////////////////


}
