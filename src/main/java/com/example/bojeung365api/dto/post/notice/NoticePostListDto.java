package com.example.bojeung365api.dto.post.notice;

import com.example.bojeung365api.dto.post.PostListDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticePostListDto implements PostListDto {
    private Long id;
    private String title;
    private Long viewCount;
    private LocalDateTime createdAt;
    private String authorNickname;
    private Integer commentCount;
}