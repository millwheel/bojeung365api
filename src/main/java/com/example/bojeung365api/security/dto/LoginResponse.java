package com.example.bojeung365api.security.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {}
