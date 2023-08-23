package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.repository.CustomerRepository;
import com.example.cafebackend.table.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    //////////////////////////////////

    public Customer createCustomer(String PhoneNumber) throws BaseException {
        /// verify
        //if(customerRepository.existsByPhoneNumber(PhoneNumber)) throw OrderException.createFail();
        if(Objects.isNull(PhoneNumber)) throw OrderException.createFail();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "C"+uuid.substring(0, 14);
        /// save
        Customer table = new Customer();
        table.setCusId(uuid);
        table.setPhoneNumber(PhoneNumber);
        return customerRepository.save(table);
    }
    //////////////////////////////////

    public Customer updateCustomer(Customer customer) throws BaseException {
        /// verify
        if(Objects.isNull(customer)) throw OrderException.createFail();
        /// save
        return customerRepository.save(customer);
    }
    //////////////////////////////////

    public Boolean existsByPhoneNumber(String PhoneNumber) {
        ///
        return customerRepository.existsByPhoneNumber(PhoneNumber);
    }

    public Optional<Customer> findCustomerById(String id){
        ///
        return customerRepository.findById(id);
    }
    //////////////////////////////////

    public List<Customer> findAllCustomer(){
        ///
        return customerRepository.findAll();
    }
    //////////////////////////////////

    public Optional<Customer> findCustomerByPhoneNumber(String phone){
        ///
        return customerRepository.findByPhoneNumber(phone);
    }
    //////////////////////////////////



    public void deleteCustomer(String cusId) {
        customerRepository.deleteById(cusId);
    }

    //////////////////////////////////
//    public boolean matchPassword(String rawPassword, String encodedPassword){
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }

}
