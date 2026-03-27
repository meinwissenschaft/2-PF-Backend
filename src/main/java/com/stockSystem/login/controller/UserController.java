package com.stockSystem.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public String getProfile(Authentication authentication){
        return "Usuario autenticado: " + authentication.getName();
    }
}
