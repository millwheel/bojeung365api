package com.example.bojeung365api.security.handler;


import com.example.bojeung365api.security.cookie.JwtCookieProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtLogoutHandler implements LogoutHandler {

    private final JwtCookieProvider jwtCookieProvider;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Cookie jwtCookie = jwtCookieProvider.createLogoutCookie();
        response.addCookie(jwtCookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
