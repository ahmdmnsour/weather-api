package com.orange.weather.advice;

import com.orange.weather.exception.EmailAlreadyExistsException;
import com.orange.weather.exception.UnauthorizedAccessException;
import com.orange.weather.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ObjectNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(ObjectNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return body;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public Map<String, String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return body;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedAccessException.class)
    public Map<String, String> handleUnauthorizedAccessException(ObjectNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return body;
    }
}
