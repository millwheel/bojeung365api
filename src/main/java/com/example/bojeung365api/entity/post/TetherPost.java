package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.tether.TetherPostRequest;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "tether_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TetherPost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;

    private String thumbnailUrl;

    public TetherPost(TetherPostRequest request, User author) {
        super(request.getTitle(), author);
        this.richBody = request.getRichBody();
        this.thumbnailUrl = request.getThumbnailUrl();
    }

    public void update(TetherPostRequest request) {
        super.update(request.getTitle());
        this.richBody = request.getRichBody();
        this.thumbnailUrl = request.getThumbnailUrl();
    }
}