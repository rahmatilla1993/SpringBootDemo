package com.example.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExistException extends AuthenticationException {
    public ExistException(String message) {
        super(message);
    }
}
