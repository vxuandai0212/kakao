package com.kakao.auth.service;

import com.kakao.auth.dto.ApiResponse;
import com.kakao.auth.dto.AuthResponse;
import com.kakao.auth.dto.LoginRequest;
import com.kakao.auth.dto.SignupRequest;

public interface IAuthenticationService {

    AuthResponse authenticateUser(LoginRequest loginRequest);

    ApiResponse registerUser(SignupRequest signupRequest);
}
