package com.kakao.social.repository;

import com.kakao.enums.AuthProvider;
import com.kakao.social.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findByEmailAndProvider(String email, AuthProvider provider);

    Optional<User> findByUsernameAndProvider(String username, AuthProvider provider);
}
