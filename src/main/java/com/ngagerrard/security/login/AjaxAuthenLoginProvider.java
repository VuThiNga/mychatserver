package com.ngagerrard.security.login;

import com.ngagerrard.manager.BaseManager;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.mysql.tables.records.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class AjaxAuthenLoginProvider implements AuthenticationProvider {
    @Autowired
    private BaseManager baseManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AjaxAuthenLogin authenLogin = (AjaxAuthenLogin) authentication;

        UsersRecord userRecord = baseManager.getDslContext().selectFrom(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(authenLogin.getPrincipal().getUsername())).fetchOne();
        if (userRecord == null) {
            throw new UsernameNotFoundException("user not exist");
        }
        if (!new BCryptPasswordEncoder().matches(authenLogin.getCredentials(), userRecord.getPassword())) {
            throw new AuthenticationServiceException("password incorrect");
        }
        baseManager.getDslContext().update(Users.USERS).set(Users.USERS.TOKEN, authenLogin.getPrincipal().getToken())
                .where(Users.USERS.ID.eq(userRecord.getId())).execute();
        return new UsernamePasswordAuthenticationToken(
                authenLogin.getPrincipal(), authenLogin.getCredentials(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(
                AjaxAuthenLogin.class);
    }
}
