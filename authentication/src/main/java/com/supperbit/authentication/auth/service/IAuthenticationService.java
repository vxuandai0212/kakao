package com.supperbit.authentication.auth.service;

import com.supperbit.authentication.auth.bean.ApiResponse;
import com.supperbit.authentication.auth.bean.AuthResponse;
import com.supperbit.authentication.auth.bean.LoginRequest;
import com.supperbit.authentication.auth.bean.SignupRequest;

public interface IAuthenticationService {

    AuthResponse authenticateUser(LoginRequest loginRequest);

    ApiResponse registerUser(SignupRequest signupRequest);
}
