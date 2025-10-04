package com.example.bojeung365api.repository.scam;

import com.example.bojeung365api.dto.post.scam.ScamReportPostListDto;
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
import static com.example.bojeung365api.entity.post.QScamReportPost.scamReportPost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class ScamReportPostRepositoryImpl implements ScamReportPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ScamReportPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ScamReportPostListDto> findList(Pageable pageable) {
        Expression<Integer> commentCountExpr = JPAExpressions
                .select(comment.id.count().intValue())
                .from(comment)
                .where(comment.post.id.eq(scamReportPost.id));

        List<ScamReportPostListDto> content = queryFactory
                .select(Projections.constructor(ScamReportPostListDto.class,
                        scamReportPost.id,
                        scamReportPost.title,
                        scamReportPost.viewCount,
                        scamReportPost.createdAt,
                        user.nickname,
                        commentCountExpr,
                        scamReportPost.siteName
                ))
                .from(scamReportPost)
                .join(scamReportPost.author, user)
                .orderBy(scamReportPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(scamReportPost.count())
                .from(scamReportPost)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
