package com.example.bojeung365api.dto.post.official;

import com.example.bojeung365api.dto.author.AuthorResponse;
import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.OfficialPost;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OfficialPostResponse implements PostResponseDto {
    private Long id;
    private String title;
    private Long viewCount;
    private AuthorResponse author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String richBody;

    public OfficialPostResponse(OfficialPost officialPost, List<CommentResponse> commentResponses) {
        this.id = officialPost.getId();
        this.title = officialPost.getTitle();
        this.viewCount = officialPost.getViewCount();
        this.author = new AuthorResponse(officialPost.getAuthor());
        this.createdAt = officialPost.getCreatedAt();
        this.updatedAt = officialPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.richBody = officialPost.getRichBody();
    }
}
