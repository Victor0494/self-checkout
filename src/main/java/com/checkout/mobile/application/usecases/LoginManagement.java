package com.checkout.mobile.application.usecases;

import com.checkout.mobile.application.gateways.LoginGateway;
import com.checkout.mobile.domain.entities.UserLogin;

public class LoginManagement {

    private final LoginGateway loginGateway;

    public LoginManagement(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    public UserLogin login(UserLogin userLogin) {
        return loginGateway.login(userLogin);
    }
}
