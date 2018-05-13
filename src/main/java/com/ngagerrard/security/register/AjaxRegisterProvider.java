package com.ngagerrard.security.register;

import com.ngagerrard.manager.BaseManager;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.mysql.tables.records.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AjaxRegisterProvider implements AuthenticationProvider {
    //custom register
    @Autowired
    private BaseManager baseManager;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AjaxAuthenRegister register = (AjaxAuthenRegister) authentication;
        //kiemtra da ton tai hay chua
        UsersRecord userRecord = baseManager.getDslContext().selectFrom(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(register.getPrincipal().getUsername())).fetchOne();
        if (userRecord != null) {
            throw new AuthenticationServiceException("user exsit");
        }
        //BCryptPasswordEncoder: đoi tuong de ma hoa password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = passwordEncoder.encode(register.getCredentials()); //register.getCredentials: chuỗi cần mã hóa
        int indexCollum = baseManager.getDslContext().insertInto(Users.USERS,
                Users.USERS.USER_NAME, Users.USERS.PASSWORD, Users.USERS.TOKEN)
                .values(register.getPrincipal().getUsername(), passwordEncode, register.getPrincipal().getToken()).execute();
        if (indexCollum <= 0) {
            throw new AuthenticationServiceException("Can not insert user into database");
        }
        return new UsernamePasswordAuthenticationToken(register.getPrincipal(), register.getCredentials());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AjaxAuthenRegister.class);
    }
}
