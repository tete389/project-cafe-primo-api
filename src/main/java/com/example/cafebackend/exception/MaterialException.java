package com.example.cafebackend.exception;

public class MaterialException extends BaseException{

    public MaterialException(String code) {
        super(code);
    }

    public static ProductException addInfoFailRequestMateNull(){
        return new ProductException("addInfo.Fail.requestMaterialNull");
    }

    public static ProductException addInfoFailMateDuplicate(){
        return new ProductException("addInfo.Fail.MaterialDuplicate");
    }

    public static ProductException delInfoFailRequestMateNull(){
        return new ProductException("deleteInfo.Fail.requestMaterialNull");
    }

    public static MaterialException createFailNameDuplicate(){
        return new MaterialException("createMaterial.Fail.NameDuplicate");
    }

    public static MaterialException createFail(){
        return new MaterialException("createMaterial.Fail");
    }

    public static MaterialException createFailRequestNull(){
        return new MaterialException("createMaterial.Fail.RequestNull");
    }

    public static MaterialException findFail(){
        return new MaterialException("findFail.DataNull");
    }

    public static MaterialException findFailRequestNull(){
        return new MaterialException("findFail.RequestNull");
    }

    public static MaterialException updateFail(){
        return new MaterialException("updateMaterial.Fail");
    }

    public static MaterialException updateFailNameDuplicate(){
        return new MaterialException("updateMaterial.Fail.NameDuplicate");
    }

    public static MaterialException deleteFail(){
        return new MaterialException("deleteMaterial.Fail");
    }
}
