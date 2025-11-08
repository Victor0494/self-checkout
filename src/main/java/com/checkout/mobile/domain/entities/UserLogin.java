package com.checkout.mobile.domain.entities;

import com.checkout.mobile.domain.valueObject.TokenJwt;

import java.util.Set;

public class UserLogin {

    private String id;
    private String username;
    private String password;
    private TokenJwt token;

    public UserLogin(TokenJwt token) {
        this.token = token;
    }

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TokenJwt getToken() {
        return token;
    }

    public void setToken(TokenJwt token) {
        this.token = token;
    }
}
