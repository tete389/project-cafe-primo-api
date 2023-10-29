package com.example.cafebackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.EmployeeException;
import com.example.cafebackend.table.Employee;
import com.example.cafebackend.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final EmployeeService employeeService;
    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public TokenService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String tokenize(Employee emp) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", emp.getEmpId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    public Employee checkTokenEmp() throws BaseException {

        Optional<String> optEmp = SecurityUtil.getCurrentUserId();
        if (optEmp.isEmpty()) {
            throw EmployeeException.accessDenied();
        }
        String empId = optEmp.get();
        Optional<Employee> emp = employeeService.findEmpById(empId);
        if (emp.isEmpty()) {
            throw EmployeeException.accountNotFound();
        }
        return emp.get();
    }
    //
    // public Boolean checkAdmin() throws BaseException{
    // Optional<String> optUser = SecurityUtil.getCurrentUserId();
    // if (optUser.isEmpty()) {
    // throw UserException.accessDenied();
    // }
    // return optUser.get().equals("Admin");
    // }
}
