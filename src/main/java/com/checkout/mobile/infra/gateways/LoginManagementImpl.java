package com.checkout.mobile.infra.gateways;

import com.checkout.mobile.application.gateways.LoginGateway;
import com.checkout.mobile.domain.entities.UserLogin;
import com.checkout.mobile.domain.valueObject.TokenJwt;
import com.checkout.mobile.infra.persistence.userLogin.UserLoginEntity;
import com.checkout.mobile.infra.persistence.userLogin.UserLoginRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class LoginManagementImpl implements LoginGateway {

    private final UserLoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;

    public LoginManagementImpl(UserLoginRepository loginRepository, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public UserLogin login(UserLogin userLogin) {
        Optional<UserLoginEntity> user = loginRepository.findByUsername(userLogin.getUsername());

        if(user.isEmpty() || !user.get().isLoginCorrect(userLogin, passwordEncoder)) {
            throw new BadCredentialsException("User or password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 500000L;

        var claims = JwtClaimsSet.builder()
                .issuer("myBackEnd")
                .subject(user.get().getId())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now).build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new UserLogin(new TokenJwt(jwtValue, expiresIn));
    }
}
