package com.example.cafebackend.exception;

public class OrderException extends BaseException{

    public OrderException(String code) {
        super(code);
    }

    public static OrderException createFail(){
        return new OrderException("createOrder.Fail");
    }

    public static OrderException createFailDataNull(){
        return new OrderException("createOrder.Fail.Data.null");
    }

    public static OrderException findFail(){
        return new OrderException("Data.Null");
    }


    public static OrderException updateFailDataNull(){
        return new OrderException("updateOrder.Fail.DataNull");
    }

    public static OrderException updateFailNotFound(){
        return new OrderException("updateOrder.Fail.NotFound");
    }


    public static OrderException deleteFail(){
        return new OrderException("deleteOrder.Fail.NotFound");
    }


}
