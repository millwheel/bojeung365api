package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.review.ReviewPostRequest;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "review_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPost extends Post {

    private String siteName;
    private String siteUrl;

    // 금액
    private LocalDate bettingDate;
    private BigDecimal bettingAmount;
    private BigDecimal dividend;
    private BigDecimal winAmount;

    // 별점
    private Integer exchangeSpeed;
    private Integer dividendRating;
    private Integer eventRating;
    private Integer reliability;

    // 본문
    @Column(columnDefinition = "text")
    private String body;

    // TODO 이미지 필드 추가
    
    public ReviewPost(ReviewPostRequest request, User author) {
        super(request.getTitle(), author);
        this.siteName = request.getSiteName();
        this.siteUrl = request.getSiteUrl();
        this.bettingDate = request.getBettingDate();
        this.bettingAmount = request.getBettingAmount();
        this.dividend = request.getDividend();
        this.winAmount = request.getWinAmount();
        this.exchangeSpeed = request.getExchangeSpeed();
        this.dividendRating = request.getDividendRating();
        this.eventRating = request.getEventRating();
        this.reliability = request.getReliability();
        this.body = request.getBody();
    }

    public void update(ReviewPostRequest request) {
        super.update(request.getTitle());
        this.siteName = request.getSiteName();
        this.siteUrl = request.getSiteUrl();
        this.bettingDate = request.getBettingDate();
        this.bettingAmount = request.getBettingAmount();
        this.dividend = request.getDividend();
        this.winAmount = request.getWinAmount();
        this.exchangeSpeed = request.getExchangeSpeed();
        this.dividendRating = request.getDividendRating();
        this.eventRating = request.getEventRating();
        this.reliability = request.getReliability();
        this.body = request.getBody();
    }
}