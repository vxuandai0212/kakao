package com.supperbit.authentication.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException {
    public EmailNotFoundException(String msg) {
        super(msg);
    }
}