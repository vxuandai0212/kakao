package com.supperbit.authentication.auth.controller;

import com.supperbit.authentication.auth.bean.ApiResponse;
import com.supperbit.authentication.auth.bean.AuthResponse;
import com.supperbit.authentication.auth.bean.LoginRequest;
import com.supperbit.authentication.auth.bean.SignupRequest;
import com.supperbit.authentication.auth.service.IAuthenticationService;
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