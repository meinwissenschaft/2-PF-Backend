package com.stockSystem.login.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.stockSystem.login.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())   //password encriptado
                .roles(user.getRole.name())     //USER o ADMIN
                .build();
    }
}
