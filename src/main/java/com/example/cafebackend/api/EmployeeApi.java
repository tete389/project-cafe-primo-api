package com.example.cafebackend.api;

import com.example.cafebackend.controller.EmployeeController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.response.ApiResponse;
import com.example.cafebackend.model.response.EmployeeResponse;
import com.example.cafebackend.table.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeApi {

    private final EmployeeController employeeController;

    public EmployeeApi(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerEmp(@RequestBody Employee request) throws BaseException {
        ApiResponse res = employeeController.createEmployee(request.getUsername(), request.getPassword(), request.getEmpName(), request.getPhoneNumber());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginEmp(@RequestBody Employee request) throws BaseException {
        ApiResponse res = employeeController.loginEmployee(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getEmployeeAll")
    public ResponseEntity<EmployeeResponse> getEmployeeAll() throws BaseException {
        EmployeeResponse res = employeeController.getEmployeeAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getEmployeeDetail")
    public ResponseEntity<EmployeeResponse> getEmployeeDetail() throws BaseException {
        EmployeeResponse res = employeeController.getEmployeeDetail();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee request) throws BaseException {
        EmployeeResponse res = employeeController.updateEmployee(request.getPassword(), request.getEmpName(), request.getPhoneNumber());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteEmployee")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@RequestBody Employee request) throws BaseException {
        EmployeeResponse res = employeeController.deleteEmployee(request.getEmpId());
        return ResponseEntity.ok(res);
    }
}
