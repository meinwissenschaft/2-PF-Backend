package com.stockSystem.login.service;

import com.stockSystem.login.entity.User;

public interface UserService {

    User findByUsername(String username);

    User save(User user);
}
