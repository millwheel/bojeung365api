package com.example.bojeung365api.dto.post.notice;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.NoticePost;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NoticePostResponse implements PostResponseDto {
    private Long id;
    private String title;
    private Long viewCount;
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private JsonNode richBody;

    public NoticePostResponse(NoticePost noticePost, List<CommentResponse> commentResponses) {
        this.id = noticePost.getId();
        this.title = noticePost.getTitle();
        this.viewCount = noticePost.getViewCount();
        this.authorNickname = noticePost.getAuthor().getNickname();
        this.createdAt = noticePost.getCreatedAt();
        this.updatedAt = noticePost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.richBody = noticePost.getRichBody();
    }
}