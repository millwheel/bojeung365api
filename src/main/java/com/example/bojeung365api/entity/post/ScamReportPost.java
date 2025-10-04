package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.scam.ScamReportPostRequest;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "scam_report_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScamReportPost extends Post {

    private String siteName;
    private String siteUrl;

    private LocalDate victimDate;
    private BigDecimal victimAmount;

    @Column(columnDefinition = "text")
    private String body;

    // TODO 이미지 연결 추가

    public ScamReportPost(ScamReportPostRequest request, User author) {
        super(request.getTitle(), author);
        this.siteName = request.getSiteName();
        this.siteUrl = request.getSiteUrl();
        this.victimDate = request.getVictimDate();
        this.victimAmount = request.getVictimAmount();
        this.body = request.getBody();
    }

    public void update(ScamReportPostRequest request) {
        super.update(request.getTitle());
        this.siteName = request.getSiteName();
        this.siteUrl = request.getSiteUrl();
        this.victimDate = request.getVictimDate();
        this.victimAmount = request.getVictimAmount();
        this.body = request.getBody();
    }
}
