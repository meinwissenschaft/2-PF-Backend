package com.stockSystem.login.service;

import com.stockSystem.login.entity.Usuario;

public interface UserService {

    Usuario findByUsername(String username);

    Usuario save(Usuario usuario);
}
