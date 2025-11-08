package com.checkout.mobile.application.gateways;

import com.checkout.mobile.domain.entities.UserLogin;

public interface LoginGateway {

    UserLogin login(UserLogin userLogin);
}
