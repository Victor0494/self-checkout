package com.checkout.mobile.infra.controller;

import com.checkout.mobile.application.usecases.LoginManagement;
import com.checkout.mobile.domain.entities.UserLogin;
import com.checkout.mobile.infra.dto.LoginRequest;
import com.checkout.mobile.infra.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginManagement loginManagement;

    public LoginController(LoginManagement loginManagement) {
        this.loginManagement = loginManagement;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserLogin login = loginManagement.login(new UserLogin(loginRequest.userName(), loginRequest.password()));

        return ResponseEntity.ok(new LoginResponse(login.getToken().getAccessToken(), login.getToken().getExpiresIn()));
    }

}
