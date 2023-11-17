package com.supperbit.authentication.auth.repository;

import com.supperbit.authentication.auth.entity.User;
import com.supperbit.authentication.auth.enums.AuthProvider;
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
