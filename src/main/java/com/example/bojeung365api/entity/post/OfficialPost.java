package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.dto.post.official.OfficialPostRequest;
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

    private Long fileId;

    public OfficialPost(OfficialPostRequest request, User author) {
        super(request.getTitle(), author);
        this.richBody = request.getRichBody();
        this.fileId = request.getFileId();
    }

    public void update(OfficialPostRequest request) {
        super.update(request.getTitle());
        this.richBody = request.getRichBody();
        this.fileId = request.getFileId();
    }
}