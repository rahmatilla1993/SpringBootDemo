package com.example.springbootdemo.handler;

import com.example.springbootdemo.dto.ErrorDto;
import com.example.springbootdemo.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public HttpEntity<ErrorDto> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorDto.builder()
                                .code(HttpStatus.NOT_FOUND.value())
                                .message(e.getMessage())
                                .path(request.getRequestURI())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}
