package com.example.springbootdemo.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class AuthDto {
    @Parameter(name = "username", required = true, description = "Tizimga kirmoqchi bo'lgan userni userneymi")
    @NotBlank(message = "Username bo'sh qoldirilmasin")
    private String username;
    @Parameter(name = "password", required = true, description = "Tizimga kirmoqchi bo'lgan userni paroli")
    @Min(value = 4, message = "Parol uzunligi 4 tadan kam bo'lmasin")
    @NotBlank(message = "Password bo'sh qoldirilmasin")
    private String password;
}
