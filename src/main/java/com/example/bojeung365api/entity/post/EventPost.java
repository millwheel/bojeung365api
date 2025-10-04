package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.event.EventPostRequest;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "event_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventPost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;

    public EventPost(EventPostRequest request, User author) {
        super(request.getTitle(), author);
        this.richBody = request.getRichBody();
    }

    public void update(EventPostRequest request) {
        super.update(request.getTitle());
        this.richBody = request.getRichBody();
    }
}
