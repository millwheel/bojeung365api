package com.example.bojeung365api.entity.post;

import com.example.bojeung365api.entity.BaseTimeEntity;
import com.example.bojeung365api.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    protected Post(String title, User author) {
        this.title = title;
        this.author = author;
    }

    protected void update(String title) {
        this.title = title;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }
}
