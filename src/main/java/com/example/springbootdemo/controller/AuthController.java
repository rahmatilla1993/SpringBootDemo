package com.example.springbootdemo.controller;

import com.example.springbootdemo.dto.LoginDto;
import com.example.springbootdemo.entity.AuthUser;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.enums.RoleName;
import com.example.springbootdemo.exception.ExistException;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserRepository authUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthUserRepository authUserRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody LoginDto dto) {
        String username = dto.getUsername();
        authUserRepository.findByUsername(username)
                .ifPresentOrElse(authUser -> {
                    throw new ExistException("Username with '%s' already taken".formatted(username));
                }, () -> {
                    Role role = roleRepository.findByRoleName(RoleName.ROLE_USER);
                    AuthUser authUser = AuthUser.builder()
                            .username(username)
                            .password(passwordEncoder.encode(dto.getPassword()))
                            .roles(List.of(role))
                            .build();
                    authUserRepository.save(authUser);
                });
        return ResponseEntity.ok("User registered successfully");
    }
}
