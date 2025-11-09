package com.example.bojeung365api.dto.post.event;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class EventPostRequest {
    private String title;
    private JsonNode richBody;
}