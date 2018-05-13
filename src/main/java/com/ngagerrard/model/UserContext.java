package com.ngagerrard.model;

public class UserContext {
    private String username;
    private String token;

    public UserContext(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken(){
        return token;
    }
}
