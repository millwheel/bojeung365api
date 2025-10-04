package com.example.bojeung365api.dto.post.review;

import java.time.LocalDateTime;

public record ReviewPostListDto(
        Long id,
        String title,
        Long viewCount,
        LocalDateTime createdAt,
        String authorNickname
) {}