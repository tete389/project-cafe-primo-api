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

    public ApiResponse createEmployee(Employee request) throws BaseException {
        String requestLoginId = request.getEmpLoginId();
        String requestPass = request.getEmpPassword();
        String requestName = request.getEmpName();
        String requestTel = request.getEmpTel();
        /// validate
        if(Objects.isNull(requestLoginId) ||  requestLoginId.isEmpty()){
            throw EmployeeException.createFailDataNull();
        }
        if(Objects.isNull(requestPass) ||  requestPass.isEmpty()){
            throw EmployeeException.createFailDataNull();
        }
        if(Objects.isNull(requestName) ||  requestName.isEmpty()){
            throw EmployeeException.createFailDataNull();
        }
        if(Objects.isNull(requestTel) ||  requestTel.isEmpty()){
            throw EmployeeException.createFailDataNull();
        }
        ///
        Employee emp =  employeeService.createEmp(requestLoginId, requestPass, requestName, requestTel);
        ApiResponse res = new ApiResponse();
        res.setAccessToken(tokenService.tokenize(emp));
        res.setMessage("Success");
        return res;
    }

    ////////////////////////////////////////////////

    public ApiResponse loginEmployee(Employee request) throws BaseException {
        String requestLoginId = request.getEmpLoginId();
        String requestPass = request.getEmpPassword();
        // check email
        Optional<Employee> empLogin = employeeService.findAllLoginId(requestLoginId);
        if (empLogin.isEmpty()) {
            throw EmployeeException.loginFail();
        }
        // check password form email
        Employee emp = empLogin.get();
//        if (!employeeService.matchPassword(requestPass, emp.getEmpPassword())) {
//            throw EmployeeException.loginFail();
//        }
        ///
        ApiResponse res = new ApiResponse();
        res.setAccessToken(tokenService.tokenize(emp));
        res.setMessage("Success");
        return res;
    }


    ////////////////////////////////////////////////

    public EmployeeResponse updateEmployee(Employee request) throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        String empName = request.getEmpName();
        String empTel = request.getEmpTel();
        /// validate
        if(Objects.isNull(empName) ||  empName.isEmpty()){
            empName = emp.getEmpName();
        }
        if(Objects.isNull(empTel) ||  empTel.isEmpty()){
            empTel = emp.getEmpTel();
        }
        ///
        Employee empRes = employeeService.updateEmp(emp, empName, empTel);
        EmployeeResponse res = new EmployeeResponse();
        res.setMessage("Update Success");
        res.setEmployee(empRes);
        return res;
    }


    ////////////////////////////////////////////////
    public EmployeeResponse getEmployee() throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        ///
        EmployeeResponse res = new EmployeeResponse();
        res.setMessage("Employee detail");
        res.setEmployee(emp);
        return res;
    }

    ////////////////////////////////////////////////

    public EmployeeResponse getAllEmployee() throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        ///
        EmployeeResponse res = new EmployeeResponse();
        List<Employee> empRes = employeeService.findAllEmp();
        res.setMessage("All Employee");
        res.setEmployee(empRes);
        return res;
    }

    ////////////////////////////////////////////////

    public EmployeeResponse deleteEmployee(Employee request) throws BaseException {
        Employee emp = tokenService.checkTokenEmp();
        ///
        EmployeeResponse res = new EmployeeResponse();
        employeeService.deleteEmp(request.getEmpId());
        res.setMessage("delete Employee");
        res.setEmployee("Success");
        return res;
    }



}
