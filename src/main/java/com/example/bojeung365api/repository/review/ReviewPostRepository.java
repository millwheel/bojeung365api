package com.example.bojeung365api.repository.review;

import com.example.bojeung365api.entity.post.ReviewPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewPostRepository extends JpaRepository<ReviewPost, Long>, ReviewPostRepositoryCustom {

}