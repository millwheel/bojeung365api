package com.example.bojeung365api.exception.handler;

import com.example.bojeung365api.exception.custom.*;
import com.example.bojeung365api.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> details = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> Optional.ofNullable(fe.getDefaultMessage()).orElse("유효하지 않습니다."),
                        (existing, ignored) -> existing,
                        LinkedHashMap::new
                ));
        String summary = details.entrySet().stream()
                .map(e -> e.getKey() + " - " + e.getValue())
                .collect(Collectors.joining(", "));
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), summary, req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ErrorResponse handleInvalidRequestException(HttpServletRequest req, InvalidRequestException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }

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
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Exception Occurred: {}", ex.getMessage());
        StackTraceElement top = ex.getStackTrace()[0];
        log.error("Top of stack trace: {}.{} ({}:{})", top.getClassName(), top.getMethodName(), top.getFileName(), top.getLineNumber());
        return ErrorResponse.of(status.value(), status.getReasonPhrase(), ex.getLocalizedMessage(), req.getRequestURI());
    }
}
