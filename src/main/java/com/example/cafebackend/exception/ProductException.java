package com.example.cafebackend.exception;

public class ProductException extends BaseException{

    public ProductException(String code) {
        super(code);
    }

    ///////
    public static ProductException createBaseFail(){
        return new ProductException("crateBase.Fail");
    }
    public static ProductException findBaseFail(){
        return new ProductException("findBase.Fail");
    }



    ///////

    public static ProductException createProductFail(){
        return new ProductException("createProduct.Fail");
    }

    public static ProductException createFailRequestFormNull(){
        return new ProductException("addProduct.Fail.Form Null");
    }

    public static ProductException createFailPriceRequestNull(){
        return new ProductException("addProduct.Fail.Price Null");
    }

    public static ProductException createFailRequestBaseNull(){
        return new ProductException("addProduct.Fail.Base Null");
    }

    public static ProductException findFailRequestProductIdNull(){
        return new ProductException("findProduct.Fail.request ProductId Null");
    }

    public static ProductException findFailRequestFormNull(){
        return new ProductException("findProduct.Fail.request Form Null");
    }

    public static ProductException findFailRequestPointNull(){
        return new ProductException("findProduct.Fail.request Bonus Point Null");
    }

    public static ProductException findFailRequestEnableNull(){
        return new ProductException("findProduct.Fail.request Enable Null");
    }

    public static ProductException findFailRequestPriceNull(){
        return new ProductException("findProduct.Fail.request Price Null");
    }

    public static ProductException updateFailProductNull(){
        return new ProductException("updateInfo.Fail.product Null");
    }



    ////////////////

    public static ProductException findProductFail(){
        return new ProductException("findProductFail.DataNull");
    }

    public static ProductException updateFailTitleDuplicate(){
        return new ProductException("updateBase.Fail.TitleDuplicate");
    }

    public static ProductException updateFailFormDuplicate(){
        return new ProductException("updateForm.Fail.FormDuplicate");
    }

    public static ProductException createFailTitleDuplicate(){
        return new ProductException("CreateBase.Fail.TitleDuplicate");
    }

    public static ProductException createFailFormDuplicate(){
        return new ProductException("CreateForm.Fail.FormDuplicate");
    }


    public static ProductException deleteFail(){
        return new ProductException("deleteProduct.Fail.NotFound");
    }


}
