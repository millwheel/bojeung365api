package com.example.bojeung365api.dto.user;

import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.entity.user.UserRole;

public record MeResponse(
        String username,
        String nickname,
        String email,
        UserRole role
) {

    public MeResponse(User user) {
        this(user.getUsername(), user.getNickname(), user.getEmail(), user.getRole());
    }
}
