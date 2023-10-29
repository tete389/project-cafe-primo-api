package com.example.cafebackend.service;

import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.repository.OrderDetailOptionRepository;
import com.example.cafebackend.table.OrderDetailOption;
import com.example.cafebackend.table.OrderDetailProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderDetailOptionService {

    private final OrderDetailOptionRepository orderDetailOptionRepository;

    public OrderDetailOptionService(OrderDetailOptionRepository orderDetailOptionRepository) {
        this.orderDetailOptionRepository = orderDetailOptionRepository;
    }

    //////////////////////////

    public OrderDetailOption createOrderDetailOption(OrderDetailProduct orderDetailProduct, String optionNameTh, String optionNameEng,
            Double price, String optionId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ODTo" + uuid.substring(0, 13);
        OrderDetailOption table = new OrderDetailOption();
        table.setOdtOptionId(uuid);
        table.setOrderDetailProduct(orderDetailProduct);
        table.setOptionNameTh(optionNameTh);
        table.setOptionNameEng(optionNameEng);
        table.setOptionPrice(price);
        table.setOptionId(optionId);
        return orderDetailOptionRepository.save(table);
    }
    /////////////////////////

    public OrderDetailOption updateOrderDetailOption(OrderDetailOption orderDetailOption) throws OptionException {
        /// verify
        if (Objects.isNull(orderDetailOption))
            throw OptionException.updateFail();
        /// save
        return orderDetailOptionRepository.save(orderDetailOption);
    }
    /////////////////////////

    public Optional<OrderDetailOption> findById(String id) {
        ///
        return orderDetailOptionRepository.findById(id);
    }
    /////////////////////////

    public List<OrderDetailOption> findAll() {
        ///
        return orderDetailOptionRepository.findAll();
    }
    /////////////////////////

    public List<OrderDetailOption> findByOrderDetailOptionBetweenDate(String start, String end) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionBetweenDate(start, end);
    }

    public List<OrderDetailOption> findByOrderDetailOptionBetweenDateStatus(String start, String end, String status) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionBetweenDateStatus(start, end, status);
    }

    public Set<String> findByOrderDetailOptionNameThBetweenDateStatus(String start, String end, String status) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionNameThBetweenDateStatus(start, end, status);
    }

    
    public Set<String> findByOrderDetailOptionNameThBetweenDate(String start, String end) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionNameThBetweenDate(start, end);
    }

    public Integer findByOrderDetailOptionCountBetweenDateStatus(String start, String end, String status, String name) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionCountBetweenDateStatus(start, end, status, name);
    }

      public Integer findByOrderDetailOptionCountBetweenDate(String start, String end, String name) {
        ///
        return orderDetailOptionRepository.findOrderDetailOptionCountBetweenDate(start, end, name);
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
