package com.kakao.gateway.configuration;

import com.kakao.gateway.exception.GatewayException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

public class GatewayExceptionHandler extends DefaultErrorWebExceptionHandler {

    public GatewayExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> responseBodyMap = new LinkedHashMap<>();
        Map<String, Object> error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        Throwable throwable = getError(request);
        int httpStatus = getHttpStatus(error);

        if (throwable instanceof Exception) {
            Exception ex = (Exception) throwable;
            responseBodyMap.put("code", 1);
            responseBodyMap.put("message", "FAILED");
            Map<String, Object> errorResponse = new LinkedHashMap<>();
            if (throwable instanceof GatewayException) {
                GatewayException gatewayException = (GatewayException) throwable;
                errorResponse.put("errorMessage", gatewayException.getErrorMessage());
                responseBodyMap.put("data", errorResponse);
            } else {
                errorResponse.put("errorMessage", ex.getMessage());
                responseBodyMap.put("data", errorResponse);
            }
        }
        return ServerResponse
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(responseBodyMap));
    }
}
