package com.sena.segurity.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class securityConfig {
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpStatus http) throws Exception{
        return http
        .csrf(csrf->csrf.disable())
        .authorizeHtt
    }
}
