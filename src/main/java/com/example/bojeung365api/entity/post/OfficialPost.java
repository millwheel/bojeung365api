package com.example.bojeung365api.entity.post;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "official_post")
@PrimaryKeyJoinColumn(name = "id")
public class OfficialPost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;

    private String thumbnailUrl;
}