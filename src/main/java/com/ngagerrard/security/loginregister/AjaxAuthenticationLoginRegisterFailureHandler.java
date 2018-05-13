package com.ngagerrard.security.loginregister;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngagerrard.Constants;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.security.exception.AuthenticationUsernamePasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationLoginRegisterFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    public AjaxAuthenticationLoginRegisterFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (e instanceof AuthenticationUsernamePasswordInvalidException) {
            objectMapper.writeValue(response.getWriter(), ResponseUtils.getBaseResponse(Constants.STATUS_CODE_USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        } else {
            objectMapper.writeValue(response.getWriter(), ResponseUtils.getBaseResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        }

    }
}
