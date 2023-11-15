package com.kakao.auth.controller;

import com.kakao.auth.dto.ApiResponse;
import com.kakao.auth.dto.AuthResponse;
import com.kakao.auth.dto.LoginRequest;
import com.kakao.auth.dto.SignupRequest;
import com.kakao.auth.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IAuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.registerUser(signupRequest));
    }
}