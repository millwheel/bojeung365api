package com.example.bojeung365api.repository.review;

import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.bojeung365api.entity.QComment.comment;
import static com.example.bojeung365api.entity.post.QReviewPost.reviewPost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class ReviewPostRepositoryImpl implements ReviewPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ReviewPostListDto> findList(Pageable pageable) {
        Expression<Integer> commentCountExpr = JPAExpressions
                .select(comment.id.count().intValue())
                .from(comment)
                .where(comment.post.id.eq(reviewPost.id));

        List<ReviewPostListDto> content = queryFactory
                .select(Projections.constructor(ReviewPostListDto.class,
                        reviewPost.id,
                        reviewPost.title,
                        reviewPost.viewCount,
                        reviewPost.createdAt,
                        user.nickname,
                        commentCountExpr,
                        reviewPost.siteName

                ))
                .from(reviewPost)
                .join(reviewPost.author, user)
                .orderBy(reviewPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(reviewPost.count())
                .from(reviewPost)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
