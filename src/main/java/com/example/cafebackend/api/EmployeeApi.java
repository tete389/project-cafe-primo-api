package com.example.cafebackend.api;

import com.example.cafebackend.controller.EmployeeController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.response.ApiResponse;
import com.example.cafebackend.model.response.EmployeeResponse;
import com.example.cafebackend.table.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Emp")
public class EmployeeApi {

    private final EmployeeController employeeController;

    public EmployeeApi(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerEmp(@RequestBody Employee request) throws BaseException {
        ApiResponse res = employeeController.createEmployee(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginEmp(@RequestBody Employee request) throws BaseException {
        ApiResponse res = employeeController.loginEmployee(request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllEmp")
    public ResponseEntity<EmployeeResponse> getAllEmp() throws BaseException {
        EmployeeResponse res = employeeController.getAllEmployee();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getEmp")
    public ResponseEntity<EmployeeResponse> getEmp() throws BaseException {
        EmployeeResponse res = employeeController.getEmployee();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateEmp")
    public ResponseEntity<EmployeeResponse> updateEmp(@RequestBody Employee request) throws BaseException {
        EmployeeResponse res = employeeController.updateEmployee(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteEmp")
    public ResponseEntity<EmployeeResponse> deleteEmp(@RequestBody Employee request) throws BaseException {
        EmployeeResponse res = employeeController.deleteEmployee(request);
        return ResponseEntity.ok(res);
    }
}
