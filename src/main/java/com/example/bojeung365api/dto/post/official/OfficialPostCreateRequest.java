package com.example.bojeung365api.dto.post.official;

public record OfficialPostCreateRequest(
        String title,
        String richBody,
        String thumbnailUrl
) {
}
