package com.kakao.social.payload;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
