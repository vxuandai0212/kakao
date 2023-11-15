package com.kakao.auth.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.auth.dto.SignupRequest;
import com.kakao.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(
    properties = {
        "spring.jpa.generate-ddl=true",
        "spring.datasource.url=jdbc:tc:mysql:8:///test"
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc()
@Testcontainers
public class AuthControllerIntegrationTest {

    @LocalServerPort
    int randomPort;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    void checkIT() throws Exception {
        var username = "username";
        var password = "password";
        var name = "name";
        var request = SignupRequest.builder()
            .username(username)
            .name(name)
            .password(password)
                .build();
        var response = mockMvc.perform(post("/auth/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));
    }
}
