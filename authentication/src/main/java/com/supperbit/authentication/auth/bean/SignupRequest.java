package com.supperbit.authentication.auth.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
    String email;
    String password;
}
