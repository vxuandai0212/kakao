package com.kakao.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
