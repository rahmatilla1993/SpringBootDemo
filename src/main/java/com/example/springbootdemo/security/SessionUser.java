package com.example.springbootdemo.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionUser {

    public Optional<String> getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SecurityUser su) {
            return Optional.of(su.getUsername());
        }
        return Optional.empty();
    }
}
