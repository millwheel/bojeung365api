package com.example.bojeung365api.dto.post.tether;

import com.example.bojeung365api.dto.post.PostListDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TetherPostListDto implements PostListDto {
    private Long id;
    private String title;
    private Long viewCount;
    private LocalDateTime createdAt;
    private String authorNickname;
    private Integer commentCount;
    private Long fileId;
}