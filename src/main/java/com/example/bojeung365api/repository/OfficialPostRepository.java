package com.example.bojeung365api.repository;

import com.example.bojeung365api.dto.post.OfficialPostListDto;
import com.example.bojeung365api.entity.post.OfficialPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OfficialPostRepository extends JpaRepository<OfficialPost, Long> {

    @Query(
            value = """
            select new com.example.bojeung365api.dto.post.OfficialPostListDto(
                op.id,
                op.title,
                op.viewCount,
                op.createdAt,
                op.thumbnailUrl,
                a.nickname
            )
            from OfficialPost op
            join op.author a
            order by op.id desc
            """,
            countQuery = """
            select count(op)
            from OfficialPost op
            """
    )
    Page<OfficialPostListDto> findList(Pageable pageable);
}