package com.kakao.auth.repository;

import com.kakao.auth.entity.User;
import com.kakao.enums.AuthProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findByEmailAndProvider(String email, AuthProvider provider);

    Optional<User> findByUsernameAndProvider(String username, AuthProvider provider);
}
