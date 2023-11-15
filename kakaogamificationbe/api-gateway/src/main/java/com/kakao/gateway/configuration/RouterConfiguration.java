package com.kakao.gateway.configuration;

import com.kakao.gateway.filter.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public RouterConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, ObjectMapper objectMapper) {
        return builder.routes()
            .route("auth",
                r -> r.path("/auth/**")
                    .filters(f -> f.filter(jwtAuthenticationFilter)
                    ).uri("lb://auth-service")
            )
            .route("social",
                r -> r.path("/social/**")
                    .filters(f -> f.filter(jwtAuthenticationFilter)
                    ).uri("lb://social-service")
            )
            .route("user",
                r -> r.path("/user/**")
                    .filters(f -> f.filter(jwtAuthenticationFilter)
                    ).uri("lb://user-service")
            )
            .build();
    }
}
