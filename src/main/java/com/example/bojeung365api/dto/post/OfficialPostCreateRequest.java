package com.example.bojeung365api.dto.post;

public record OfficialPostCreateRequest(
        String title,
        String richBody,
        String thumbnailUrl
) {
}
