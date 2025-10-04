package com.example.bojeung365api.dto.post.review;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReviewPostRequest {
    private String title;
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
}
