package com.example.bojeung365api.entity.comment;

import com.example.bojeung365api.entity.BaseTimeEntity;
import com.example.bojeung365api.entity.post.Post;
import com.example.bojeung365api.entity.user.User;
import lombok.Getter;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false, columnDefinition = "text")
    private String body;

}