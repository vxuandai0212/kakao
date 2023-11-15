package com.kakao.social.payload;

import lombok.Data;

@Data
public class SignupRequest {
    String name;
    String username;
    String password;
}
