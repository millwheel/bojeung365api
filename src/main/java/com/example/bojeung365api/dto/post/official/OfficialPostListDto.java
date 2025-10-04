package com.example.bojeung365api.dto.post.official;

import com.example.bojeung365api.dto.post.PostListDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfficialPostListDto implements PostListDto {
    private Long id;
    private String title;
    private Long viewCount;
    private LocalDateTime createdAt;
    private String authorNickname;
    private Integer commentCount;
    private String thumbnailUrl;
}