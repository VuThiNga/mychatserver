package com.ngagerrard.security.withoutloginregister;

import com.ngagerrard.manager.BaseManager;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.mysql.tables.records.UsersRecord;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BaseManager baseManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;

        Condition condition = Users.USERS.USER_NAME.eq(token.getCredentials().getUsername());
        UsersRecord userRecord = baseManager.getDslContext().selectFrom(Users.USERS)
                .where(condition).fetchOne();
        if (userRecord == null) {
            throw new AuthenticationServiceException("fount fount user");
        }
        if ( !token.getCredentials().getToken().equals(userRecord.getToken())) {
            throw new AuthenticationServiceException("token invalidate");
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(JwtAuthenticationToken.class);
    }
}