package com.example.springbootdemo.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final MailSenderService mailSenderService;
    private final AuthRepository authRepository;

    @Autowired
    public AuthController(MailSenderService mailSenderService, AuthRepository authRepository) {
        this.mailSenderService = mailSenderService;
        this.authRepository = authRepository;
    }

    @PostMapping("/register")
    public HttpEntity<String> register(@RequestBody AuthUserDto dto) {
        authRepository.findByUsername(dto.getUsername())
                .ifPresentOrElse(authUser -> {
                    throw new RuntimeException("Username with '%s' already taken!");
                }, () -> {
                    AuthUser authUser = AuthUser.builder()
                            .username(dto.getUsername())
                            .password(dto.getPassword())
                            .isActivate(false)
                            .build();
                    authRepository.save(authUser);
                    mailSenderService.sendFreeMarkerMail(dto.getUsername());
                });
        return ResponseEntity.ok("User registered successfully!\n Please check your email inbox!");
    }

    @GetMapping("/activate")
    public HttpEntity<String> activate(@RequestParam String token) {
        byte[] bytes = Base64.getDecoder().decode(token);
        String username = new String(bytes);
        authRepository.findByUsername(username)
                .ifPresent(authUser -> {
                    authUser.setActivate(true);
                    authRepository.save(authUser);
                });
        return ResponseEntity.ok("User successfully activated self account!");
    }
}
