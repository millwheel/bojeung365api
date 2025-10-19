package com.example.bojeung365api.dto.post.official;

import lombok.Data;

@Data
public class OfficialPostRequest {
    private String title;
    private String richBody;
    private Long fileId;
}