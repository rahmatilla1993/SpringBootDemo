package com.example.springbootdemo.exception;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private String message;
    private int code;
    private String path;
    private LocalDateTime createdAt = LocalDateTime.now();
}
