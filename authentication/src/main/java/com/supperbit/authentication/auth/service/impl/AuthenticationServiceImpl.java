package com.supperbit.authentication.auth.service.impl;

import com.supperbit.authentication.auth.bean.ApiResponse;
import com.supperbit.authentication.auth.bean.AuthResponse;
import com.supperbit.authentication.auth.bean.LoginRequest;
import com.supperbit.authentication.auth.bean.SignupRequest;
import com.supperbit.authentication.auth.entity.User;
import com.supperbit.authentication.auth.enums.AuthProvider;
import com.supperbit.authentication.auth.exception.BadRequestException;
import com.supperbit.authentication.auth.repository.UserRepository;
import com.supperbit.authentication.auth.security.TokenProvider;
import com.supperbit.authentication.auth.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new AuthResponse(tokenProvider.createToken(authentication));
    }

    @Override
    @Transactional
    public ApiResponse registerUser(SignupRequest signupRequest) {
        if(Boolean.TRUE.equals(userRepository.existsByEmail(signupRequest.getEmail()))) {
            throw new BadRequestException("email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setProvider(AuthProvider.LOCAL);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return new ApiResponse(true, "User registered successfully");
    }
}

