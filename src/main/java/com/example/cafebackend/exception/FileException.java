package com.example.cafebackend.exception;

public class FileException extends BaseException{

    public FileException(String code) {
        super(code);
    }

    public static FileException createFail(){
        return new FileException("create.File.Fail");
    }

    public static FileException findFail(){
        return new FileException("File.NotFound");
    }

    public static FileException fileNull(){
        return new FileException("File.NotFound");
    }

    public static FileException fileMaxSize(){
        return new FileException("File.Max.Size");
    }

    public static FileException updateFail(){
        return new FileException("update.File.Fail");
    }

    public static FileException updateFailTypes(){
        return new FileException("update.File.Fail.UnSupportTypes");
    }

    public static FileException deleteFail(){
        return new FileException("delete.File.Fail");
    }
}
