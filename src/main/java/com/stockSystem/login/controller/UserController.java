package com.stockSystem.login.controller;

import com.stockSystem.login.dto.UserResponse;
import com.stockSystem.login.entity.Usuario;

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

        Usuario usuario = userService.findByUsername(username);

        return new UserResponse(
          usuario.getId(),
          usuario.getUsername(),
          usuario.getEmail(),
          usuario.getRole().name()
        );
    }
}
