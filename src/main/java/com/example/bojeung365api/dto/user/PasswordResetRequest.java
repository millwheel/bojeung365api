package com.example.bojeung365api.dto.user;

import jakarta.validation.constraints.NotNull;

public record PasswordResetRequest (
        @NotNull
        String nickname,

        @NotNull
        String email
) {}

