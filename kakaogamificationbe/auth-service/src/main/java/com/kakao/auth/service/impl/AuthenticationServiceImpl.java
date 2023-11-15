package com.kakao.auth.service.impl;

import com.kakao.auth.dto.ApiResponse;
import com.kakao.auth.dto.AuthResponse;
import com.kakao.auth.dto.LoginRequest;
import com.kakao.auth.dto.SignupRequest;
import com.kakao.auth.entity.User;
import com.kakao.auth.exception.BadRequestException;
import com.kakao.auth.repository.UserRepository;
import com.kakao.auth.security.TokenProvider;
import com.kakao.auth.service.IAuthenticationService;
import com.kakao.enums.AuthProvider;
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
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new AuthResponse(tokenProvider.createToken(authentication));
    }

    @Override
    @Transactional
    public ApiResponse registerUser(SignupRequest signupRequest) {
        if(Boolean.TRUE.equals(userRepository.existsByUsername(signupRequest.getUsername()))) {
            throw new BadRequestException("username address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(signupRequest.getPassword());
        user.setProvider(AuthProvider.LOCAL);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return new ApiResponse(true, "User registered successfully");
    }
}
