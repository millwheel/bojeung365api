package com.example.bojeung365api.entity.post;

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

    // TODO 이미지 연결 추가
}