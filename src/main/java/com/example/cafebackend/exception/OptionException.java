package com.example.cafebackend.exception;

public class OptionException extends BaseException{

    public OptionException(String code) {
        super(code);
    }

    public static OptionException createFail(){
        return new OptionException("createAddOn.Fail");
    }

    public static OptionException findFailRequestNull(){
        return new OptionException("find.Fail.RequestNull");
    }

    public static OptionException findFail(){
        return new OptionException("find.Fail.InfoNull");
    }

    public static ProductException addInfoFailRequestAddOnNull(){
        return new ProductException("addInfo.Fail.requestAddOnNull");
    }

    public static ProductException delInfoFailRequestOptionNull(){
        return new ProductException("deleteInfo.Fail.requestOptionNull");
    }

    public static OptionException updateFail(){
        return new OptionException("updateAdditional.Fail");
    }

    public static OptionException updateFailDuplicate(){
        return new OptionException("update.Fail.duplicate");
    }

    public static OptionException deleteFail(){
        return new OptionException("deleteAdditional.Fail");
    }
}
