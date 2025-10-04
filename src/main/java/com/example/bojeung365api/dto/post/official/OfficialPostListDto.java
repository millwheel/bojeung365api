package com.example.bojeung365api.dto.post.official;

import java.time.LocalDateTime;

public record OfficialPostListDto(
        Long id,
        String title,
        Long viewCount,
        LocalDateTime createdAt,
        String thumbnailUrl,
        String authorNickname
) {}