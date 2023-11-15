package com.kakao.gateway.filter;

import com.kakao.gateway.exception.GatewayException;
import com.kakao.gateway.exception.JwtTokenMalformedException;
import com.kakao.gateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        HttpHeaders headers = request.getHeaders();
//
//        if (headers.containsKey("appId") && headers.containsKey("deviceId")) {
//            Predicate<ServerHttpRequest> isApiSecured = r -> Stream.of("/auth")
//                    .noneMatch(uri -> r.getURI().getPath().contains(uri));
//
//            if (isApiSecured.test(request)) {
//                if (headers.containsKey("Authorization")) {
//                    final String token = Optional.ofNullable(
//                            request.getHeaders().getOrEmpty("Authorization").get(0)
//                    ).orElse("").substring(7);
//                    try {
//                        if (!token.isEmpty()) {
//                            jwtUtils.validateToken(token.substring(7));
//                        }
//                    } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
//                        ServerHttpResponse response = exchange.getResponse();
//                        response.setStatusCode(HttpStatus.FORBIDDEN);
//                        return Mono.error(new GatewayException(e.getMessage()));
//                    }
//
//                    Claims claims = jwtUtils.getClaims(token);
//                    String loggedInUser = String.valueOf(claims.get("sub"));
//                    exchange.getRequest().mutate().header("username", loggedInUser).build();
//                } else {
//                    ServerHttpResponse response = exchange.getResponse();
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return Mono.error(new GatewayException("Missing JWT token!"));
//                }
//            }
//        } else {
//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.BAD_REQUEST);
//            return Mono.error(new GatewayException("Missing device information!"));
//        }
        return chain.filter(exchange);
    }
}
