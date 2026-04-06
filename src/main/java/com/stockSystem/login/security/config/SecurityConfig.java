package com.stockSystem.login.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.stockSystem.login.security.jwt.JwtFilter;
import com.stockSystem.login.security.jwt.JwtAutheticationEntryPoint;

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final JwtAutheticationEntryPoint jwtAutheticationEntryPoint;

    public SecurityConfig(JwtFilter jwtFilter,
                          JwtAutheticationEntryPoint jwtAutheticationEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.jwtAutheticationEntryPoint = jwtAutheticationEntryPoint;
    }
    //PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //AuthenticationManager (necesario para login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                //desactiva el CSRF (API Rest)
                .csrf(csrf -> csrf.disable())
                //manejo de errores (EntryPoint)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAutheticationEntryPoint)
                )
                //rutas publicas:
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                //Filtro JWT (antes del login de spring)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}