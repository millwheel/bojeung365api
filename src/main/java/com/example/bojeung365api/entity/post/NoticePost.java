package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.notice.NoticePostRequest;
import com.example.bojeung365api.entity.user.User;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Entity
@Table(name = "notice_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticePost extends Post {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private JsonNode richBody;

    public NoticePost(NoticePostRequest request, User author) {
        super(request.getTitle(), author);
        this.richBody = request.getRichBody();
    }

    public void update(NoticePostRequest request) {
        super.update(request.getTitle());
        this.richBody = request.getRichBody();
    }
}