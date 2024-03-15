package com.example.springbootdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(name = "Error Dto", description = "Xatolik haqida xabar beruvchi dto")
public class ErrorDto {
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;

    public ErrorDto(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
