package com.dwibagus.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserConfig {

    @Bean
    public UserDetailsService users(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$95C6WnLIuHLx14EaSLilfuy6x8q6Pv/KrrLyKz9zK5w9lcdML7rzC")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
