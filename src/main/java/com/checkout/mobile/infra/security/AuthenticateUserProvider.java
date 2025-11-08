package com.checkout.mobile.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUserProvider {

    private final JwtDecoder jwtDecoder;

    public AuthenticateUserProvider(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            throw  new RuntimeException("Authentication object is not of type JwtAuthenticationToken");
        }

        String tokenValue = jwtAuth.getToken().getTokenValue();
        Jwt jwt = jwtDecoder.decode(tokenValue);

        return jwt.getSubject();

    }
}
