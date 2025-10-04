package com.example.bojeung365api.dto.post.official;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.entity.post.OfficialPost;

import java.time.LocalDateTime;
import java.util.List;

public record OfficialPostResponse(
        Long id,
        String title,
        Long viewCount,
        String authorNickname,
        String richBody,
        String thumbnailUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CommentResponse> commentResponses
) {
    public OfficialPostResponse(OfficialPost officialPost, List<CommentResponse> commentResponses) {
        this(
                officialPost.getId(),
                officialPost.getTitle(),
                officialPost.getViewCount(),
                officialPost.getAuthor().getNickname(),
                officialPost.getRichBody(),
                officialPost.getThumbnailUrl(),
                officialPost.getCreatedAt(),
                officialPost.getUpdatedAt(),
                commentResponses
        );
    }
}
