package com.example.springbootdemo.config;

import com.example.springbootdemo.dto.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        String path = request.getRequestURI();
        String errorMsg = authException.getMessage();
        int errorCode = 401;
        ErrorDto errorDto = new ErrorDto(errorMsg, errorCode, path);
        response.setStatus(errorCode);
        ServletOutputStream outputStream = response.getOutputStream();
        objectMapper.writeValue(outputStream, errorDto);
    }
}
