package com.example.bojeung365api.repository.official;

import com.example.bojeung365api.entity.post.OfficialPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficialPostRepository extends JpaRepository<OfficialPost, Long>, OfficialPostRepositoryCustom {

}