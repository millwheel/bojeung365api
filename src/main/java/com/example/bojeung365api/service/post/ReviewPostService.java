package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import com.example.bojeung365api.dto.post.review.ReviewPostRequest;
import com.example.bojeung365api.dto.post.review.ReviewPostResponse;
import com.example.bojeung365api.entity.post.ReviewPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.repository.review.ReviewPostRepository;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReviewPostService extends AbstractPostService<
        ReviewPost,
        ReviewPostRequest,
        ReviewPostRequest,
        ReviewPostListDto,
        ReviewPostResponse
        > {

    private final ReviewPostRepository reviewPostRepository;

    public ReviewPostService(
            UserRepository userRepository,
            CommentService commentService,
            ReviewPostRepository reviewPostRepository
    ) {
        super(userRepository, commentService);
        this.reviewPostRepository = reviewPostRepository;
    }

    @Override
    protected String notFoundTarget() {
        return "reviewPost";
    }

    @Override
    protected JpaRepository<ReviewPost, Long> repository() {
        return reviewPostRepository;
    }

    @Override
    public Page<ReviewPostListDto> getPostListDtos(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return reviewPostRepository.findList(pageable);
    }

    @Override
    protected ReviewPostResponse toResponse(ReviewPost reviewPost, List<CommentResponse> commentResponses) {
        return new ReviewPostResponse(reviewPost, commentResponses);
    }

    @Override
    protected ReviewPost createEntity(ReviewPostRequest request, User author) {
        return new ReviewPost(request, author);
    }

    @Override
    protected void updateEntity(ReviewPost reviewPost, ReviewPostRequest request) {
        reviewPost.update(request);
    }
}