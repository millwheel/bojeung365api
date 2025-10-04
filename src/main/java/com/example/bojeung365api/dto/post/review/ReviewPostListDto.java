package com.example.bojeung365api.dto.post.review;

import com.example.bojeung365api.dto.post.PostListDto;
import com.example.bojeung365api.dto.post.PostResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewPostListDto implements PostListDto {
    private Long id;
    private String title;
    private Long viewCount;
    private LocalDateTime createdAt;
    private String authorNickname;
}

