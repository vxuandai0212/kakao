package com.kakao.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GatewayException extends Exception {
    private String errorMessage;
}
