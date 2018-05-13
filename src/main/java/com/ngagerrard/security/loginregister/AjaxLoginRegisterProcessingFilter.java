package com.ngagerrard.security.loginregister;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngagerrard.Constants;
import com.ngagerrard.model.request.LoginRequest;
import com.ngagerrard.security.exception.AuthenticationUsernamePasswordInvalidException;
import com.ngagerrard.security.login.AjaxAuthenLogin;
import com.ngagerrard.security.register.AjaxAuthenRegister;
import org.jooq.tools.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginRegisterProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private AjaxAuthenticationLoginRegisterSuccessHandler successHandler;

    private AuthenticationFailureHandler failureHandler;

    private ObjectMapper objectMapper;

    public AjaxLoginRegisterProcessingFilter(RequestMatcher matcher, AjaxAuthenticationLoginRegisterSuccessHandler successHandler,
                                             AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
        super(matcher);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        LoginRequest loginRequest = objectMapper.readValue(httpServletRequest.getReader(), LoginRequest.class);
        if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
            throw new AuthenticationUsernamePasswordInvalidException("invalid param for login");
        }
        if (httpServletRequest.getRequestURI().equals(Constants.ENPOINT_LOGIN)) {
            AjaxAuthenLogin authenLogin = new AjaxAuthenLogin(loginRequest.getUsername(), loginRequest.getPassword());
            return getAuthenticationManager().authenticate(authenLogin);
        } else {
            //check min length password
            if (loginRequest.getPassword().length() < Constants.MIN_LEHGTH_PASSWORD) {
                throw new AuthenticationServiceException("Length password must than 6 character");
            }
            AjaxAuthenRegister register = new AjaxAuthenRegister(loginRequest.getUsername(), loginRequest.getPassword());
            return getAuthenticationManager().authenticate(register);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }


}

