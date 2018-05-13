package com.ngagerrard.model.response;

public class BaseResponse {
    public static final int CODE_SUCCESS = 0;
    //==0 la thanh cong
    public static final int ERROR_PARAM = 1;
//......
    private int statusCode;
    private String msg;
    private Object data;

    public BaseResponse(int statusCode, String msg, Object data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

}

