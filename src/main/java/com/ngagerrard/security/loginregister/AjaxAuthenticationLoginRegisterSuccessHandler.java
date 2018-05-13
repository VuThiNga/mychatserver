package com.ngagerrard.security.loginregister;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngagerrard.Constants;
import com.ngagerrard.model.UserContext;
import com.ngagerrard.model.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AjaxAuthenticationLoginRegisterSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String tokenString = ((UserContext) authenticationToken.getPrincipal()).getToken();
        Map<String, String> map = new HashMap<>();
        map.put(Constants.TOKEN_NAME, tokenString);
        objectMapper.writeValue(httpServletResponse.getWriter(), ResponseUtils.getBaseResponse(map));
        clearAuthenticationAttributes(request);
    }

}
