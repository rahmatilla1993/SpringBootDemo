package com.example.springbootdemo.security;

import com.example.springbootdemo.dto.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String path = request.getRequestURI();
        String errorMsg = accessDeniedException.getMessage();
        int errorCode = 403;
        ErrorDto errorDto = new ErrorDto(errorMsg, errorCode, path);
        response.setStatus(errorCode);
        ServletOutputStream outputStream = response.getOutputStream();
        objectMapper.writeValue(outputStream, errorDto);
    }
}
