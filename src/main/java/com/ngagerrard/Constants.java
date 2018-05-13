package com.ngagerrard;

public interface Constants {
    String KEY_TOKEN = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
    int STATUS_CODE_SUCCESS = 0;
    String MSG_SUCCESS = "success";
    String ENPOINT_LOGIN = "/auth/login";
    String ENPOINT_REGISTER = "/auth/register";
    String ENPOINT_MATCH_API = "/api/**";

    long TIME_TOKEN_EXPIRE = 3600000000L;
    String TOKEN_NAME = "token";
    int MIN_LEHGTH_PASSWORD = 6;

    int STATUS_CODE_TOKEN_IN_VALID = 10;
    int STATUS_CODE_EXPIRED_TOKEN = 20;
    int STATUS_CODE_USERNAME_OR_PASSWORD_INVALID = 30;
    int MAX_FEED = 20;
}
