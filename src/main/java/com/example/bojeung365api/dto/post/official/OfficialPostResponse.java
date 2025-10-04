package com.example.bojeung365api.dto.post.official;

import com.example.bojeung365api.entity.post.OfficialPost;

public record OfficialPostResponse(
        Long id,
        String title,
        Long viewCount,
        String authorNickname,
        String richBody,
        String thumbnailUrl
) {
    public OfficialPostResponse(OfficialPost officialPost) {
        this(
                officialPost.getId(),
                officialPost.getTitle(),
                officialPost.getViewCount(),
                officialPost.getAuthor().getNickname(),
                officialPost.getRichBody(),
                officialPost.getThumbnailUrl()
        );
    }
}
