package com.example.bojeung365api.entity.post;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "tether_post")
@PrimaryKeyJoinColumn(name = "id")
public class TetherPost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;

    private String thumbnailUrl;
}