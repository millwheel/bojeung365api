package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.PostListDto;
import com.example.bojeung365api.dto.post.PostResponseDto;
import com.example.bojeung365api.entity.post.Post;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.service.CommentService;
import com.example.bojeung365api.util.AuthorityChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public abstract class AbstractPostService<
        T extends Post,
        CreateRequest,
        UpdateRequest,
        L extends PostListDto,
        R extends PostResponseDto
        > {

    protected final UserRepository userRepository;
    protected final CommentService commentService;
    protected final PostViewCountService postViewCountService;

    protected abstract JpaRepository<T, Long> repository();

    protected abstract R toResponse(T post, List<CommentResponse> comments, boolean editable);

    protected abstract T createEntity(CreateRequest request, User author);
    protected abstract void updateEntity(T post, UpdateRequest request);
    protected abstract String notFoundTarget();

    protected T getPostOrThrow(Long id) {
        return repository().findById(id)
                .orElseThrow(() -> new DataNotFoundException(notFoundTarget()));
    }

    public abstract Page<L> getPostListDtos(int page, int size);

    public R getPostResponse(Long id, UserDetails userDetails) {
        T post = getPostOrThrow(id);
        boolean editable = AuthorityChecker.checkEditable(post.getAuthor(), userDetails);
        postViewCountService.increaseAsync(post.getId());
        List<CommentResponse> comments = commentService.getCommentResponses(id, userDetails);
        return toResponse(post, comments, editable);
    }

    @Transactional
    public void createPage(CreateRequest request, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        repository().save(createEntity(request, author));
    }

    @Transactional
    public void updatePage(Long id, UpdateRequest request, String username) {
        T post = getPostOrThrow(id);
        User requestUser = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        AuthorityChecker.validateEditable(post.getAuthor(), requestUser);
        updateEntity(post, request);
    }

    @Transactional
    public void deletePage(Long id, String username) {
        T post = getPostOrThrow(id);
        User requestUser = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        AuthorityChecker.validateEditable(post.getAuthor(), requestUser);
        commentService.deleteCommentsCascade(id);
        repository().delete(post);
    }
}