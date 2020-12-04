package com.example.dbprojectmain.result;

public class ResultFactory {
    public static Result buildSuccessFactory(Object data){
        return buildResult(ResultCode.SUCCESS,"成功",data);
    }

    public static Result buildFailFactory(String  message){
        return buildResult(ResultCode.FAIL,message,null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data){
        return buildResult(resultCode.code,message,data);
    }


    public static Result buildResult(int resultCode, String message, Object data){
        return new Result(resultCode,message,data);
    }

}