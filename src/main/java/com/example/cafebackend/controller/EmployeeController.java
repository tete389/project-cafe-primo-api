package com.example.cafebackend.controller;


import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.model.response.ApiResponse;
import com.example.cafebackend.model.response.EmployeeResponse;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@AllArgsConstructor
@Service
public class EmployeeController {

    private EmployeeService employeeService;

    private TokenService tokenService;

    ////////////////////////////////////////////////

    public ApiResponse createEmployee(String username, String password, String empName, String phone) throws BaseException {
        /// validate
        if(Objects.isNull(username) || username.isEmpty()) throw EmployeeException.createFailDataNull();
        if(Objects.isNull(password) || password.isEmpty()) throw EmployeeException.createFailDataNull();
        if(Objects.isNull(empName) || empName.isEmpty()) throw EmployeeException.createFailDataNull();
        if(Objects.isNull(phone) || phone.isEmpty()) throw EmployeeException.createFailDataNull();
        /// check username
        if (employeeService.existsByUsername(username)) throw EmployeeException.createFailUsernameDuplicated();
        Employee emp =  employeeService.createEmployee(username, password, empName, phone);
        /// res
        ApiResponse res = new ApiResponse();
        res.setAccessToken(tokenService.tokenize(emp));
        res.setMessage("create Success");
        return res;
    }
    ////////////////////////////////////////////////

    public ApiResponse loginEmployee(String username, String password) throws BaseException {
        /// validate
        if(Objects.isNull(username) || username.isEmpty()) throw EmployeeException.createFailDataNull();
        if(Objects.isNull(password) || password.isEmpty()) throw EmployeeException.createFailDataNull();
        // check username
        Optional<Employee> empLogin = employeeService.findUsername(username);
        if (empLogin.isEmpty()) throw EmployeeException.loginFail();
        // check password form username
        Employee emp = empLogin.get();
        if(!emp.getPassword().equals(password)) throw EmployeeException.loginFail();
        /// res
        ApiResponse res = new ApiResponse();
        res.setAccessToken(tokenService.tokenize(emp));
        res.setMessage("login Success");
        return res;
    }
    ////////////////////////////////////////////////

    public EmployeeResponse updateEmployee(String password, String empName, String phone) throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        /// validate
        if(Objects.isNull(empName) || empName.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(password) || password.isEmpty()) throw EmployeeException.updateFailDataNull();
        if(Objects.isNull(phone) || phone.isEmpty()) throw EmployeeException.updateFailDataNull();
        /// check old value
        if (!emp.getPassword().equals(password)){
            emp.setPassword(password);
        }
        if (!emp.getEmpName().equals(empName)){
            emp.setEmpName(empName);
        }
        if (!emp.getPhoneNumber().equals(phone)){
            emp.setPhoneNumber(phone);
        }
        /// update emp
        Employee empRes = employeeService.updateEmployee(emp);
        /// res
        EmployeeResponse res = new EmployeeResponse();
        res.setMessage("Update Success");
        res.setEmployee(empRes);
        return res;
    }
    ////////////////////////////////////////////////

    public EmployeeResponse getEmployeeDetail() throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        ///
        EmployeeResponse res = new EmployeeResponse();
        res.setMessage("Employee detail");
        res.setEmployee(emp);
        return res;
    }

    ////////////////////////////////////////////////

    public EmployeeResponse getEmployeeAll() throws BaseException {
        System.out.println("1");
        tokenService.checkTokenEmp();
        ///
        EmployeeResponse res = new EmployeeResponse();
        List<Employee> empRes = employeeService.findAllEmp();
        res.setMessage("All Employee");
        res.setEmployee(empRes);
        return res;
    }

    ////////////////////////////////////////////////

    public EmployeeResponse deleteEmployee(String empId) throws BaseException {
        tokenService.checkTokenEmp();
        ///
        EmployeeResponse res = new EmployeeResponse();
        employeeService.deleteEmp(empId);
        res.setMessage("delete Employee");
        res.setEmployee("Success");
        return res;
    }



}
