package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import com.example.bojeung365api.dto.post.review.ReviewPostRequest;
import com.example.bojeung365api.dto.post.review.ReviewPostResponse;
import com.example.bojeung365api.entity.post.ReviewPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.repository.ReviewPostRepository;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.util.AuthorityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewPostService {

    private final ReviewPostRepository reviewPostRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    public Page<ReviewPostListDto> getBoard(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return reviewPostRepository.findList(pageable);
    }

    public ReviewPostResponse getPostResponse(Long postId) {
        ReviewPost reviewPost = getPost(postId);
        reviewPost.increaseViewCount();
        List<CommentResponse> commentResponses = commentService.getCommentResponses(postId);
        return new ReviewPostResponse(reviewPost, commentResponses);
    }

    private ReviewPost getPost(Long postId) {
        return reviewPostRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("official post"));
    }

    @Transactional
    public void createPage(ReviewPostRequest request, Long requestorId) {
        User author = userRepository.findById(requestorId)
                .orElseThrow(() -> new DataNotFoundException("user"));
        ReviewPost reviewPost = new ReviewPost(request, author);
        reviewPostRepository.save(reviewPost);
    }

    @Transactional
    public void updatePage(Long postId, ReviewPostRequest request) {
        ReviewPost reviewPost = getPost(postId);
        reviewPost.update(request);
    }

    @Transactional
    public void deletePage(Long postId, Long requestorId) {
        ReviewPost reviewPost = getPost(postId);
        // TODO 관리자는 열외처리할 것
        AuthorityValidator.validateMySelf(reviewPost.getAuthor(), requestorId);
        commentService.deleteCommentsCascade(postId);
        reviewPostRepository.delete(reviewPost);
    }
}
