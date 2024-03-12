package com.example.springbootdemo.controller;

import com.example.springbootdemo.security.JwtTokenUtil;
import com.example.springbootdemo.dto.AuthDto;
import com.example.springbootdemo.entity.AuthUser;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.enums.RoleName;
import com.example.springbootdemo.exception.ExistException;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserRepository authUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(AuthUserRepository authUserRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.authUserRepository = authUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody AuthDto dto) {
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

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody AuthDto dto) {
        String username = dto.getUsername();
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, dto.getPassword());
        try {
            Map<String, String> map = new HashMap<>();
            authenticationManager.authenticate(authenticationToken);
            String token = String.format("Bearer %s", jwtTokenUtil.getToken(username));
            map.put(username, token);
            return ResponseEntity.ok(map);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
