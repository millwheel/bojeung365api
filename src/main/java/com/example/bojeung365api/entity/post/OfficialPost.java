package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.official.OfficialPostCreateRequest;
import com.example.bojeung365api.dto.post.official.OfficialPostUpdateRequest;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "official_post")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OfficialPost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;

    private String thumbnailUrl;

    public OfficialPost(OfficialPostCreateRequest request, User author) {
        super(request.title(), author);
        this.richBody = request.richBody();
        this.thumbnailUrl = request.thumbnailUrl();
    }

    public void update(OfficialPostUpdateRequest request) {
        super.update(request.title());
        this.richBody = request.richBody();
        this.thumbnailUrl = request.thumbnailUrl();
    }
}