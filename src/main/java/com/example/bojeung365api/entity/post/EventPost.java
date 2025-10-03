package com.example.bojeung365api.entity.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "notice_post")
@PrimaryKeyJoinColumn(name = "id")
public class EventPost extends Post {

    @Column(columnDefinition = "jsonb")
    private String richBody;
}
