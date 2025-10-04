package com.example.bojeung365api.dto.post.official;

public record OfficialPostUpdateRequest(
        String title,
        String richBody,
        String thumbnailUrl
) {}
