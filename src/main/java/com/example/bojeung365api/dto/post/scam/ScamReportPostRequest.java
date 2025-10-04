package com.example.bojeung365api.dto.post.scam;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ScamReportPostRequest {
    private String title;
    private String siteName;
    private String siteUrl;
    private LocalDate victimDate;
    private BigDecimal victimAmount;
    private String body;
}