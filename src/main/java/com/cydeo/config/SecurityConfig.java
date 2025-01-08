package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(httpRequests -> httpRequests
                        .requestMatchers("/api/v1/user/**").hasAuthority("Admin")
                        .requestMatchers("/api/v1/project/**").hasAuthority("Manager")
                        .requestMatchers("/api/v1/task/employee/**").hasAuthority("Employee")
                        .requestMatchers("/api/v1/task/**").hasAuthority("Manager")
                        .anyRequest().authenticated())
                .build();
    }















}
