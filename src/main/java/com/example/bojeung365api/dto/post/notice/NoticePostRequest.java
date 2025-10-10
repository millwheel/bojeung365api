package com.example.bojeung365api.dto.post.notice;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class NoticePostRequest {
    private String title;
    private JsonNode richBody;
}