package com.stockSystem.login.controller;

import com.stockSystem.login.dto.UserResponse;
import com.stockSystem.login.entity.User;

import com.stockSystem.login.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserResponse getProfile(Authentication authentication) {

        String username = authentication.getName();

        User user = userService.findByUsername(username);

        return new UserResponse(
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          user.getRole().name()
        );
    }
}
