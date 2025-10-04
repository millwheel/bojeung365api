package com.example.bojeung365api.dto.post.scam;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.ScamReportPost;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScamReportPostResponse implements PostResponseDto {
    private Long id;
    private String title;
    private Long viewCount;
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String richBody;
    private String thumbnailUrl;

    public ScamReportPostResponse(ScamReportPost scamReportPost, List<CommentResponse> commentResponses) {
        this.id = scamReportPost.getId();
        this.title = scamReportPost.getTitle();
        this.viewCount = scamReportPost.getViewCount();
        this.authorNickname = scamReportPost.getAuthor().getNickname();
        this.createdAt = scamReportPost.getCreatedAt();
        this.updatedAt = scamReportPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.richBody = scamReportPost.getRichBody();
        this.thumbnailUrl = scamReportPost.getThumbnailUrl();
    }
}