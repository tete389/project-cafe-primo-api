package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.repository.EmployeeRepository;
import com.example.cafebackend.table.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    //private final PasswordEncoder passwordEncoder;

    //////////////////////////////////

    public Optional<Employee> findEmpById(String id){
        return employeeRepository.findById(id);
    }

    //////////////////////////////////
    public List<Employee> findAllEmp(){
        return employeeRepository.findAll();
    }
    //////////////////////////////////

    public Optional<Employee> findUsername(String LoginId){
        return employeeRepository.findByUsername(LoginId);
    }
    //////////////////////////////////

    public Employee createEmployee(String username, String pass, String name, String phone) throws BaseException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "E"+uuid.substring(0, 14);
        /// save
        Employee table = new Employee();
        table.setEmpId(uuid);
        table.setUsername(username);
        table.setPassword(pass);
        table.setEmpName(name);
        table.setPhoneNumber(phone);
        return employeeRepository.save(table);
    }
    //////////////////////////////////

    public Employee updateEmployee(Employee emp) throws EmployeeException {
        /// verify
        if (Objects.isNull(emp)) throw EmployeeException.updateFailDataNull();
        /// save
        return employeeRepository.save(emp);
    }
    //////////////////////////////////

    public Boolean existsByUsername(String username) throws EmployeeException {
        /// verify
        return employeeRepository.existsByUsername(username);
    }

    public void deleteEmp(String empId) {
         employeeRepository.deleteById(empId);
    }

    //////////////////////////////////
//    public boolean matchPassword(String rawPassword, String encodedPassword){
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }

}
