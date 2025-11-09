package com.example.bojeung365api.dto.post.scam;

import com.example.bojeung365api.dto.author.AuthorResponse;
import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.ScamReportPost;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScamReportPostResponse implements PostResponseDto {
    private Long id;
    private String title;
    private Long viewCount;
    private AuthorResponse author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String siteName;
    private String siteUrl;
    private LocalDate victimDate;
    private BigDecimal victimAmount;
    private String body;
    private boolean editable;

    public ScamReportPostResponse(ScamReportPost scamReportPost, List<CommentResponse> commentResponses, boolean editable) {
        this.id = scamReportPost.getId();
        this.title = scamReportPost.getTitle();
        this.viewCount = scamReportPost.getViewCount();
        this.author = new AuthorResponse(scamReportPost.getAuthor());
        this.createdAt = scamReportPost.getCreatedAt();
        this.updatedAt = scamReportPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.siteName = scamReportPost.getSiteName();
        this.siteUrl = scamReportPost.getSiteUrl();
        this.victimDate = scamReportPost.getVictimDate();
        this.victimAmount = scamReportPost.getVictimAmount();
        this.body = scamReportPost.getBody();
        this.editable = editable;
    }
}