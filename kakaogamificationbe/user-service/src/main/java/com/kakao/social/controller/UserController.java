package com.kakao.social.controller;

import com.kakao.social.exception.ResourceNotFoundException;
import com.kakao.social.entity.User;
import com.kakao.social.repository.UserRepository;
import com.kakao.social.security.CurrentUser;
import com.kakao.social.security.UserPrincipal;
import com.kakao.social.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(userService.getLoggedInUser());
    }
}
