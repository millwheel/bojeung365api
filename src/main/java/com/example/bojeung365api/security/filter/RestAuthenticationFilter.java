package com.example.bojeung365api.security.filter;

import com.example.bojeung365api.security.dto.LoginRequest;
import com.example.bojeung365api.security.token.RestAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(
                PathPatternRequestMatcher.withDefaults()
                        .matcher(HttpMethod.POST, defaultFilterProcessesUrl)
        );
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new IllegalArgumentException("Authentication method not supported: " + request.getMethod());
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        String username = loginRequest.username();
        String password = loginRequest.password();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new AuthenticationServiceException("아이디 또는 패스워드가 비어있습니다.");
        }

        RestAuthenticationToken token = new RestAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(token);
    }
}
