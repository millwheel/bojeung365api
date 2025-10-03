package com.example.bojeung365api.entity.post;

import jakarta.persistence.*;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "scam_report_post")
@PrimaryKeyJoinColumn(name = "id")
public class ScamReportPost extends Post {

    private String siteName;
    private String siteUrl;

    private LocalDate victimDate;
    private BigDecimal victimAmount;

    @Column(columnDefinition = "text")
    private String body;

    // TODO 이미지 연결 추가
}
