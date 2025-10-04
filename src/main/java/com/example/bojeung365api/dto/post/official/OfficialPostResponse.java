package com.example.bojeung365api.dto.post.official;

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
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String richBody;
    private String thumbnailUrl;

    public OfficialPostResponse(OfficialPost officialPost, List<CommentResponse> commentResponses) {
        this.id = officialPost.getId();
        this.title = officialPost.getTitle();
        this.viewCount = officialPost.getViewCount();
        this.authorNickname = officialPost.getAuthor().getNickname();
        this.createdAt = officialPost.getCreatedAt();
        this.updatedAt = officialPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.richBody = officialPost.getRichBody();
        this.thumbnailUrl = officialPost.getThumbnailUrl();
    }
}
