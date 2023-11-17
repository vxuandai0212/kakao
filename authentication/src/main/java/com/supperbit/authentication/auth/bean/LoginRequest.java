package com.supperbit.authentication.auth.bean;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
