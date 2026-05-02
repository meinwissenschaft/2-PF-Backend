package com.stockSystem.login.repository;

import java.util.Optional;

import com.stockSystem.login.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
