package com.example.bojeung365api.security.filter;

import com.example.bojeung365api.security.dto.LoginRequest;
import com.example.bojeung365api.security.handler.JwtAuthenticationFailureHandler;
import com.example.bojeung365api.security.handler.JwtAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.bojeung365api.security.AuthConstant.LOGIN_URL;

@Component
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public JwtLoginFilter(AuthenticationManager authenticationManager,
                          JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler,
                          JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler) {
        super(LOGIN_URL);
        this.objectMapper = new ObjectMapper();
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
        setAuthenticationFailureHandler(jwtAuthenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
