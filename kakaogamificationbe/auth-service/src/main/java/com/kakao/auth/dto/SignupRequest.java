package com.kakao.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
    String name;
    String username;
    String password;
}
