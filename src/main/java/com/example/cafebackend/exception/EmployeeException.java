package com.example.cafebackend.exception;

public class EmployeeException extends BaseException{

    public EmployeeException(String code) {
        super(code);
    }

    public static EmployeeException createFailUsernameDuplicated(){
        return new EmployeeException("createEmployee.Fail.Username.Duplicated");
    }

    public static EmployeeException createFailNameDuplicated(){
        return new EmployeeException("createEmployee.Fail.name.Duplicated");
    }

    public static EmployeeException createFailDataNull(){
        return new EmployeeException("createEmployee.Fail.Data.Null");
    }
    public static EmployeeException updateFailDataNull(){
        return new EmployeeException("updateEmployee.Fail.Data.Null");
    }

    public static EmployeeException loginFail(){
        return new EmployeeException("login.Fail");
    }

    public static EmployeeException accessDenied(){
        return new EmployeeException("Access.denied");
    }

    public static EmployeeException accountNotFound(){
        return new EmployeeException("Account.NotFound");
    }
}

