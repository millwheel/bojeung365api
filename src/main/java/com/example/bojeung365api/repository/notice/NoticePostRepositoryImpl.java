package com.example.bojeung365api.repository.notice;

import com.example.bojeung365api.dto.post.notice.NoticePostListDto;
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
import static com.example.bojeung365api.entity.post.QNoticePost.noticePost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class NoticePostRepositoryImpl implements NoticePostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticePostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<NoticePostListDto> findList(Pageable pageable) {
        Expression<Integer> commentCountExpr = JPAExpressions
                .select(comment.id.count().intValue())
                .from(comment)
                .where(comment.post.id.eq(noticePost.id));

        List<NoticePostListDto> content = queryFactory
                .select(Projections.constructor(NoticePostListDto.class,
                        noticePost.id,
                        noticePost.title,
                        noticePost.viewCount,
                        noticePost.createdAt,
                        user.nickname,
                        commentCountExpr
                ))
                .from(noticePost)
                .join(noticePost.author, user)
                .orderBy(noticePost.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(noticePost.count())
                .from(noticePost)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
