package com.example.bojeung365api.repository.event;

import com.example.bojeung365api.dto.post.event.EventPostListDto;
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
import static com.example.bojeung365api.entity.post.QEventPost.eventPost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class EventPostRepositoryImpl implements EventPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public EventPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<EventPostListDto> findList(Pageable pageable) {
        Expression<Integer> commentCountExpr = JPAExpressions
                .select(comment.id.count().intValue())
                .from(comment)
                .where(comment.post.id.eq(eventPost.id));

        List<EventPostListDto> content = queryFactory
                .select(Projections.constructor(EventPostListDto.class,
                        eventPost.id,
                        eventPost.title,
                        eventPost.viewCount,
                        eventPost.createdAt,
                        user.nickname,
                        commentCountExpr
                ))
                .from(eventPost)
                .join(eventPost.author, user)
                .orderBy(eventPost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(eventPost.count())
                .from(eventPost)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
