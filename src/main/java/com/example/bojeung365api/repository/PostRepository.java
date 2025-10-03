package com.example.bojeung365api.repository;

import com.example.bojeung365api.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
