package com.stockSystem.login.service.impl;

import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockSystem.login.dto.LoginRequest;
import com.stockSystem.login.dto.RegisterRequest;
import com.stockSystem.login.entity.Role;
import com.stockSystem.login.entity.Usuario;
import com.stockSystem.login.repository.UserRepository;
import com.stockSystem.login.security.jwt.JwtUtil;
import com.stockSystem.login.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    //Login
    @Override
    public String login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        return jwtUtil.generateToken(request.getUsername());
    }

    //Register:
    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());

        //encriptación:
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole(Role.USER);
        userRepository.save(usuario);
    }
}
