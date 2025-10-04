package com.example.bojeung365api.repository;

import com.example.bojeung365api.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository <T extends Post> extends JpaRepository<T, Long> {
    Page<?> findList(Pageable pageable);
}
