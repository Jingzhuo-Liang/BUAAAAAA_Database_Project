package com.example.dbprojectmain.result;

public class Result {
    //响应码
    private int code;
    private String message;
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int resultCode, String message, Object data) {
        this.code = resultCode;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
