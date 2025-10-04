package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.notice.NoticePostRequest;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "notice_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticePost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;

    public NoticePost(NoticePostRequest request, User author) {
        super(request.getTitle(), author);
        this.richBody = request.getRichBody();
    }

    public void update(NoticePostRequest request) {
        super.update(request.getTitle());
        this.richBody = request.getRichBody();
    }
}