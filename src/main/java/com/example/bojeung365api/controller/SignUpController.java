package com.example.bojeung365api.controller;

import com.example.bojeung365api.dto.SignUpRequest;
import com.example.bojeung365api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public void register(@Valid @RequestBody SignUpRequest request) {
        userService.signUp(request.username(), request.password(), request.email(), request.nickname());
    }
}
