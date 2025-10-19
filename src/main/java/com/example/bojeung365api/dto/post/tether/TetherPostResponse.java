package com.example.bojeung365api.dto.post.tether;

import com.example.bojeung365api.dto.author.AuthorResponse;
import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.TetherPost;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TetherPostResponse implements PostResponseDto {
    private Long id;
    private String title;
    private Long viewCount;
    private AuthorResponse author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String richBody;

    public TetherPostResponse(TetherPost tetherPost, List<CommentResponse> commentResponses) {
        this.id = tetherPost.getId();
        this.title = tetherPost.getTitle();
        this.viewCount = tetherPost.getViewCount();
        this.author = new AuthorResponse(tetherPost.getAuthor());
        this.createdAt = tetherPost.getCreatedAt();
        this.updatedAt = tetherPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.richBody = tetherPost.getRichBody();
    }
}