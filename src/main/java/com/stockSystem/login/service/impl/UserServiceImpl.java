package com.stockSystem.login.service.impl;

import org.springframework.stereotype.Service;

import com.stockSystem.login.entity.Usuario;
import com.stockSystem.login.repository.UserRepository;
import com.stockSystem.login.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    @Override
    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }
}
