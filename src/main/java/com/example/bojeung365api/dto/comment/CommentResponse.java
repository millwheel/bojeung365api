package com.example.bojeung365api.dto.comment;

import com.example.bojeung365api.entity.comment.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String authorNickname,
        String body,
        LocalDateTime createdAt
) {

    public CommentResponse(Comment comment) {
        this (
                comment.getId(),
                comment.getAuthor().getNickname(),
                comment.getBody(),
                comment.getCreatedAt()
        );
    }
}
