package com.example.springbootdemo.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Auth Dto", description = "Ro'yxatdan o'tish yoki login qilish uchun dto")
public class AuthDto {
    @Parameter(name = "username", description = "Tizimga kirmoqchi bo'lgan userni userneymi")
    private String username;
    @Parameter(name = "password", description = "Tizimga kirmoqchi bo'lgan userni paroli")
    private String password;
}
