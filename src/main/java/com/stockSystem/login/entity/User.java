package com.stockSystem.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
}


