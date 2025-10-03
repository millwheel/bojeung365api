package com.example.bojeung365api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank @Size(min = 3, max = 100)
        String username,

        @NotBlank
        @Size(min = 6, max = 72, message = "비밀번호는 6자이상이어야 합니다.")
        String password,

        @Email @Size(max = 100)
        String email,

        @NotBlank @Size(min = 2, max = 100)
        String nickname
) {}
