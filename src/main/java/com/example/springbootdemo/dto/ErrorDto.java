package com.example.springbootdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
