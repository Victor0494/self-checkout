package com.checkout.mobile.infra.persistence.userLogin;

import com.checkout.mobile.domain.entities.UserLogin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "tb_users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Column(unique = true)
    private String username;
    private String password;

    public UserLoginEntity(String document, String password) {
        this.username = document;
        this.password = password;
    }

    public boolean isLoginCorrect(UserLogin userLogin, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userLogin.getPassword(), this.password);
    }
}
