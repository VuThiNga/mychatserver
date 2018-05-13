package com.ngagerrard.model.response;

import com.ngagerrard.Constants;

public class ResponseUtils {
    public static BaseResponse getBaseResponse(Object data) {
        return new BaseResponse(Constants.STATUS_CODE_SUCCESS, Constants.MSG_SUCCESS, data);
    }


    public static  BaseResponse getBaseResponse(int statusCode, String message) {
        return new BaseResponse(statusCode, message, null);
    }
}
