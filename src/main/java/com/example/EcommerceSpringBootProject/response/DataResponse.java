package com.example.EcommerceSpringBootProject.response;

public class DataResponse {

    private int code;
    private String message;
    private Object data;
    private String  errorMessage;

    public DataResponse(int code, String message, Object data, String errorMessage) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
