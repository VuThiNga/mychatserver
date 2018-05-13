package com.ngagerrard.security.withoutloginregister;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngagerrard.Constants;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.security.exception.JwtExpiredTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxTokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    public AjaxTokenAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        if (e instanceof BadCredentialsException) {
            objectMapper.writeValue(response.getWriter(), ResponseUtils.getBaseResponse(Constants.STATUS_CODE_TOKEN_IN_VALID, e.getMessage()));
        }else if ( e instanceof JwtExpiredTokenException) {
            objectMapper.writeValue(response.getWriter(), ResponseUtils.getBaseResponse(Constants.STATUS_CODE_EXPIRED_TOKEN, e.getMessage()));
        } else {
            objectMapper.writeValue(response.getWriter(), ResponseUtils.getBaseResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        }


    }
}
