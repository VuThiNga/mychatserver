package com.ngagerrard.security.register;

import com.ngagerrard.Constants;
import com.ngagerrard.model.UserContext;
import com.ngagerrard.security.Utils;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AjaxAuthenRegister extends AbstractAuthenticationToken {
    //khong luu truc tiep password, luu dang hash
    private UserContext userContext;
    private String password;
    //private String gmail;

    public AjaxAuthenRegister(String username, String password) {
        super(null);
        this.password = password;
        String token = Utils.getToken(username, Constants.KEY_TOKEN);
        userContext = new UserContext(username, token);
    }

    @Override
    public UserContext getPrincipal() {
        return userContext;
    }

    @Override
    public String getCredentials() {
        return password;
    }
}
