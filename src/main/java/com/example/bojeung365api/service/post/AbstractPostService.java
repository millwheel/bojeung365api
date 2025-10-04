package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostListDto;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.Post;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.service.CommentService;
import com.example.bojeung365api.util.AuthorityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public abstract class AbstractPostService<
        T extends Post,
        CreateReq,
        UpdateReq,
        L extends PostListDto,
        R extends PostResponseDto
        > {

    protected final UserRepository userRepository;
    protected final CommentService commentService;
    protected abstract JpaRepository<T, Long> repository();

    public abstract Page<L> getBoard(int page);

    protected abstract R toResponse(T post, List<CommentResponse> comments);

    protected abstract T createEntity(CreateReq req, User author);
    protected abstract void updateEntity(T post, UpdateReq req);

    protected T getPostOrThrow(Long id) {
        return repository().findById(id)
                .orElseThrow(() -> new DataNotFoundException("post"));
    }

    @Transactional
    public R getPostResponse(Long id) {
        T post = getPostOrThrow(id);
        post.increaseViewCount();
        List<CommentResponse> comments = commentService.getCommentResponses(id);
        return toResponse(post, comments);
    }

    @Transactional
    public void createPage(CreateReq req, Long requestorId) {
        User author = userRepository.findById(requestorId)
                .orElseThrow(() -> new DataNotFoundException("user"));
        repository().save(createEntity(req, author));
    }

    @Transactional
    public void updatePage(Long id, UpdateReq req) {
        T post = getPostOrThrow(id);
        updateEntity(post, req);
    }

    @Transactional
    public void deletePage(Long id, Long requestorId) {
        T post = getPostOrThrow(id);
        AuthorityValidator.validateMySelf(post.getAuthor(), requestorId); // TODO: 관리자 예외 추가
        commentService.deleteCommentsCascade(id);
        repository().delete(post);
    }
}