package com.example.bojeung365api.exception.handler;

import com.example.bojeung365api.exception.custom.ConflictException;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.exception.custom.InvalidAuthorityException;
import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleNotFoundException(HttpServletRequest req, UserNotFoundException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidAuthorityException.class)
    public ErrorResponse handleInvalidAuthorityException(HttpServletRequest req, InvalidAuthorityException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public ErrorResponse handleNotFoundException(HttpServletRequest req, DataNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse handleConflictException(HttpServletRequest req, ConflictException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleException(HttpServletRequest req, RuntimeException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        log.error("Exception Occurred: {}", ex.getMessage());
        StackTraceElement top = ex.getStackTrace()[0];
        log.error("Top of stack trace: {}.{} ({}:{})", top.getClassName(), top.getMethodName(), top.getFileName(), top.getLineNumber());
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }
}
