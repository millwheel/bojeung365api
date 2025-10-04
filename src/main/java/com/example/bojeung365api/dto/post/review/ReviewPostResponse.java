package com.example.bojeung365api.dto.post.review;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.entity.post.ReviewPost;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewPostResponse {
    private Long id;
    private String title;
    private Long viewCount;
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> commentResponses;
    private String siteName;
    private String siteUrl;
    private LocalDate bettingDate;
    private BigDecimal bettingAmount;
    private BigDecimal dividend;
    private BigDecimal winAmount;
    private Integer exchangeSpeed;
    private Integer dividendRating;
    private Integer eventRating;
    private Integer reliability;
    private String body;

    public ReviewPostResponse(ReviewPost reviewPost, List<CommentResponse> commentResponses) {
        this.id = reviewPost.getId();
        this.title = reviewPost.getTitle();
        this.viewCount = reviewPost.getViewCount();
        this.authorNickname = reviewPost.getAuthor().getNickname();
        this.createdAt = reviewPost.getCreatedAt();
        this.updatedAt = reviewPost.getUpdatedAt();
        this.commentResponses = commentResponses;
        this.siteName = reviewPost.getSiteName();
        this.siteUrl = reviewPost.getSiteUrl();
        this.bettingDate = reviewPost.getBettingDate();
        this.bettingAmount = reviewPost.getBettingAmount();
        this.dividend = reviewPost.getDividend();
        this.winAmount = reviewPost.getWinAmount();
        this.exchangeSpeed = reviewPost.getExchangeSpeed();
        this.dividendRating = reviewPost.getDividendRating();
        this.eventRating = reviewPost.getEventRating();
        this.reliability = reviewPost.getReliability();
        this.body = reviewPost.getBody();
    }
}
