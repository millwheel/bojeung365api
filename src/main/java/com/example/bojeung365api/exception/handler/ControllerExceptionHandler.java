package com.example.bojeung365api.exception.handler;

import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleNotFoundException(HttpServletRequest req, UserNotFoundException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }
}
