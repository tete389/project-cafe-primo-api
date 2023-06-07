package com.example.cafebackend.exception;

public class CategoryException extends BaseException{

    public CategoryException(String code) {
        super(code);
    }

    public static CategoryException createFail(){
        return new CategoryException("createCategory.Fail");
    }


    public static ProductException addInfoFailRequestCateNull(){
        return new ProductException("addInfo.Fail.requestCategoryNull");
    }
    public static CategoryException addInfoFailCategoryNull(){
        return new CategoryException("addInfo.Fail.categoryNull");
    }

    public static ProductException delInfoFailRequestCateNull(){
        return new ProductException("delInfo.Fail.requestCategoryNull");
    }
    public static CategoryException delInfoFailCategoryNull(){
        return new CategoryException("delInfo.Fail.categoryNull");
    }

    public static CategoryException findFail(){
        return new CategoryException("Data.Null");
    }

    public static CategoryException updateFail(){
        return new CategoryException("updateCategory.Fail");
    }

    public static CategoryException deleteFail(){
        return new CategoryException("deleteCategory.Fail");
    }
}
