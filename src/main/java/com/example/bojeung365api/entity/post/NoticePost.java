package com.example.bojeung365api.entity.post;

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
}