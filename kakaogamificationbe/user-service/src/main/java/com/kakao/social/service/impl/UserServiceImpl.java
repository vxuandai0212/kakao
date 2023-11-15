package com.kakao.social.service.impl;

import com.kakao.social.entity.User;
import com.kakao.social.exception.ResourceNotFoundException;
import com.kakao.social.repository.UserRepository;
import com.kakao.social.security.UserPrincipal;
import com.kakao.social.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public User getLoggedInUser() {
        var currentUser  = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(currentUser.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
    }

}
