package com.example.cafebackend.api;

import com.example.cafebackend.controller.EmployeeController;
import com.example.cafebackend.controller.SettingShopController;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.model.request.ForEmployeeRequest;
import com.example.cafebackend.model.response.ApiResponse;
import com.example.cafebackend.model.response.EmployeeResponse;
import com.example.cafebackend.model.response.MessageResponse;
import com.example.cafebackend.table.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeApi {

    private final EmployeeController employeeController;

    private final SettingShopController shopController;

    public EmployeeApi(EmployeeController employeeController, SettingShopController shopController) {
        this.employeeController = employeeController;
        this.shopController = shopController;
    }

    //////////////////////////////////////////////////////////

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerEmp(@RequestBody ForEmployeeRequest request) throws BaseException {
        ApiResponse res = employeeController.createEmployee(request.getUsername(), request.getPassword(), request.getEmpName(), request.getPhoneNumber());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginEmp(@RequestBody ForEmployeeRequest request) throws BaseException {
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

    @PutMapping("/updateEmployee")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody ForEmployeeRequest request) throws BaseException {
        EmployeeResponse res = employeeController.updateEmployee(request.getPassword(), request.getEmpName(), request.getPhoneNumber());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteEmployee")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@RequestBody Employee request) throws BaseException {
        EmployeeResponse res = employeeController.deleteEmployee(request.getEmpId());
        return ResponseEntity.ok(res);
    }
    ////////////////////////////////////////

    @GetMapping("/getSetting")
    public ResponseEntity<MessageResponse> getSetting() throws BaseException {
        MessageResponse res = shopController.getSettingShop();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateSetting")
    public ResponseEntity<MessageResponse> updateSetting(@RequestParam("setShopId") String setShopId,
                                                         @RequestParam("spendRate") String pointSpendRate,
                                                         @RequestParam("collectRate") String pointCollectRate,
                                                         @RequestParam("vatRate") String vatRate,
                                                         @RequestParam("openDate") String openDate,
                                                         @RequestParam("closedDate") String closedDate) throws BaseException {
        MessageResponse res = shopController.updateSettingShop(setShopId, pointSpendRate, pointCollectRate, vatRate, openDate, closedDate);
        return ResponseEntity.ok(res);
    }
}
