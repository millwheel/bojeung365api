package com.example.bojeung365api.repository.official;

import com.example.bojeung365api.dto.post.official.OfficialPostListDto;
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
import static com.example.bojeung365api.entity.post.QOfficialPost.officialPost;
import static com.example.bojeung365api.entity.user.QUser.user;

public class OfficialPostRepositoryImpl implements OfficialPostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OfficialPostRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<OfficialPostListDto> findList(Pageable pageable) {
        Expression<Integer> commentCountExpr = JPAExpressions
                .select(comment.id.count().intValue())
                .from(comment)
                .where(comment.post.id.eq(officialPost.id));

        List<OfficialPostListDto> content = queryFactory
                .select(Projections.constructor(OfficialPostListDto.class,
                        officialPost.id,
                        officialPost.title,
                        officialPost.viewCount,
                        officialPost.createdAt,
                        user.nickname,
                        commentCountExpr,
                        officialPost.thumbnailUrl
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
