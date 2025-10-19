package com.example.bojeung365api.dto.author;

import com.example.bojeung365api.entity.user.User;
import lombok.Data;

@Data
public class AuthorResponse {
    private Long id;
    private String username;
    private String nickname;

    public AuthorResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
