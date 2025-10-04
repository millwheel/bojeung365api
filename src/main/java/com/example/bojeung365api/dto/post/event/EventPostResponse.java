package com.example.bojeung365api.dto.post.event;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.EventPost;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventPostResponse implements PostResponseDto {
    private Long id;
    private String title;
    private Long viewCount;
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String richBody;
    private String thumbnailUrl;

    public EventPostResponse(EventPost eventPost, List<CommentResponse> commentResponses) {
        this.id = eventPost.getId();
        this.title = eventPost.getTitle();
        this.viewCount = eventPost.getViewCount();
        this.authorNickname = eventPost.getAuthor().getNickname();
        this.createdAt = eventPost.getCreatedAt();
        this.updatedAt = eventPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.richBody = eventPost.getRichBody();
        this.thumbnailUrl = eventPost.getThumbnailUrl();
    }
}