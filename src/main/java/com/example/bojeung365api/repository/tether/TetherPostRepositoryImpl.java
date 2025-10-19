package com.example.bojeung365api.repository.tether;

import com.example.bojeung365api.dto.post.tether.TetherPostListDto;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.bojeung365api.entity.comment.QComment.comment;
import static com.example.bojeung365api.entity.post.QTetherPost.tetherPost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class TetherPostRepositoryImpl implements TetherPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TetherPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<TetherPostListDto> findList(Pageable pageable) {
        Expression<Integer> commentCountExpr = JPAExpressions
                .select(comment.id.count().intValue())
                .from(comment)
                .where(comment.post.id.eq(tetherPost.id));

        List<TetherPostListDto> content = queryFactory
                .select(Projections.constructor(TetherPostListDto.class,
                        tetherPost.id,
                        tetherPost.title,
                        tetherPost.viewCount,
                        tetherPost.createdAt,
                        user.nickname,
                        commentCountExpr,
                        tetherPost.fileId
                ))
                .from(tetherPost)
                .join(tetherPost.author, user)
                .orderBy(tetherPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(tetherPost.count())
                .from(tetherPost)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
