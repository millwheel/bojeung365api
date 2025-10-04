package com.example.bojeung365api.dto.post.scam;

import lombok.Data;

@Data
public class ScamReportPostRequest {
    private String title;
    private String richBody;
    private String thumbnailUrl;
}