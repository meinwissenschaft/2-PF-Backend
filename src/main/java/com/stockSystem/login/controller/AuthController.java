package com.stockSystem.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockSystem.login.dto.AuthResponse;
import com.stockSystem.login.dto.LoginRequest;
import com.stockSystem.login.dto.RegisterRequest;
import com.stockSystem.login.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    //Constructores con inyección de dependencias:
    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    //LOGIN:
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(new AuthResponse(token));
    }
    //Register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Usuario registrado correctamente!");
    }
}
