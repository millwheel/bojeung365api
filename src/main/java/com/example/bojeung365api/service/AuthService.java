package com.example.bojeung365api.service;

import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.ConflictException;
import com.example.bojeung365api.exception.custom.InvalidRequestException;
import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.security.dto.LoginRequest;
import com.example.bojeung365api.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(LoginRequest loginRequest) {
        if (loginRequest.username() == null || loginRequest.username().isBlank()
                || loginRequest.password() == null || loginRequest.password().isBlank()) {
            throw new InvalidRequestException("Username and password are required");
        }
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new ConflictException("잘못된 아이디 또는 비밀번호입니다.");
        }

        return jwtService.generateAccessToken(user);
    }
}
