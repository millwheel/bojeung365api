package com.example.bojeung365api.dto.post.scam;

import com.example.bojeung365api.dto.post.PostListDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScamReportPostListDto implements PostListDto {
    private Long id;
    private String title;
    private Long viewCount;
    private LocalDateTime createdAt;
    private String thumbnailUrl;
    private String authorNickname;
}