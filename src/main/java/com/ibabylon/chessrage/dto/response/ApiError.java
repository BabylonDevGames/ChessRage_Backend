package com.ibabylon.chessrage.dto.response;

public class ApiError {
    private String message;
    private String errorCode;

    public ApiError(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
