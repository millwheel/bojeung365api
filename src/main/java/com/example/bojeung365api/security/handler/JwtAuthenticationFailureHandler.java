package com.example.bojeung365api.security.handler;

import com.example.bojeung365api.exception.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;


@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final ObjectMapper om = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String error = HttpStatus.UNAUTHORIZED.getReasonPhrase();
        String message = getErrorMessage(exception);
        String path = request.getRequestURI();

        ErrorResponse errorResponse = new ErrorResponse(
                OffsetDateTime.now(),
                status,
                error,
                message,
                path
        );

        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        om.writeValue(response.getWriter(), errorResponse);
    }

    private String getErrorMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return "Invalid username or password";
        } else if (exception instanceof DisabledException) {
            return "User account is disabled";
        } else if (exception instanceof LockedException) {
            return "User account is locked";
        } else if (exception instanceof AccountExpiredException) {
            return "User account has expired";
        } else if (exception instanceof CredentialsExpiredException) {
            return "User credentials have expired";
        } else {
            return "Authentication failed";
        }
    }
}
