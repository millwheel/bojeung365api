package com.example.bojeung365api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest (
        @NotBlank
        String currentPassword,

        @NotBlank
        @Size(min = 6, max = 72, message = "비밀번호는 6자이상이어야 합니다.")
        String newPassword
) {}
