package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.repository.EmployeeRepository;
import com.example.cafebackend.table.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


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

    public Optional<Employee> findAllLoginId(String LoginId){
        return employeeRepository.findByEmpLoginId(LoginId);
    }
    //////////////////////////////////

    public Employee createEmp(String loginId, String pass, String name, String tel) throws BaseException {
        /// verify
        if(employeeRepository.existsByEmpLoginId(loginId)){
            throw EmployeeException.createFailLoginIdDuplicated();
        }
        if(employeeRepository.existsByEmpName(name)){
            throw EmployeeException.createFailNameDuplicated();
        }
        /// save
        Employee table = new Employee();
        table.setEmpName(loginId);
        table.setEmpPassword(pass);
        table.setEmpName(name);
        table.setEmpTel(tel);
        return employeeRepository.save(table);
    }
    //////////////////////////////////

    public Employee updateEmp(Employee emp, String name, String tel) throws EmployeeException {
        /// verify
        if(employeeRepository.existsByEmpName(name)){
            if(!emp.getEmpName().equals(name)){
                throw EmployeeException.updateFailDataNull();
            }
        }
        /// save
        emp.setEmpName(name);
        emp.setEmpTel(tel);
        return employeeRepository.save(emp);
    }
    //////////////////////////////////

    public void deleteEmp(String empId) {
         employeeRepository.deleteById(empId);
    }

    //////////////////////////////////
//    public boolean matchPassword(String rawPassword, String encodedPassword){
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }

}
