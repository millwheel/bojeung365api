package com.example.bojeung365api.security.handler;

import com.example.bojeung365api.security.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = jwtService.generateAccessToken((UserDetails) authentication.getPrincipal());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"accessToken\":\"" + token + "\",\"tokenType\":\"Bearer\"}");
    }
}
