package com.example.bojeung365api.repository.review;

import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.bojeung365api.entity.post.QOfficialPost.officialPost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class ReviewPostRepositoryImpl implements ReviewPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ReviewPostListDto> findList(Pageable pageable) {
        List<ReviewPostListDto> content = queryFactory
                .select(Projections.constructor(ReviewPostListDto.class,
                        officialPost.id,
                        officialPost.title,
                        officialPost.viewCount,
                        officialPost.createdAt,
                        user.nickname
                ))
                .from(officialPost)
                .join(officialPost.author, user)
                .orderBy(officialPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(officialPost.count())
                .from(officialPost)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
