package com.example.bojeung365api.repository.event;

import com.example.bojeung365api.entity.post.EventPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPostRepository extends JpaRepository<EventPost, Long>, EventPostRepositoryCustom {

}