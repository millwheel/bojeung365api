package com.example.bojeung365api.repository.tether;

import com.example.bojeung365api.entity.post.TetherPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TetherPostRepository extends JpaRepository<TetherPost, Long>, TetherPostRepositoryCustom {

}