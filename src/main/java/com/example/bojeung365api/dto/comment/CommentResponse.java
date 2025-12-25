package com.example.bojeung365api.dto.comment;

import com.example.bojeung365api.entity.comment.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String authorNickname,
        String body,
        LocalDateTime createdAt,
        boolean editable
) {

    public CommentResponse(Comment comment, boolean editable) {
        this (
                comment.getId(),
                comment.getAuthor().getNickname(),
                comment.getBody(),
                comment.getCreatedAt(),
                editable
        );
    }
}
