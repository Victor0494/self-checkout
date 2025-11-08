package com.checkout.mobile.infra.persistence.userLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, String> {

    Optional<UserLoginEntity> findByUsername(String username);
}
