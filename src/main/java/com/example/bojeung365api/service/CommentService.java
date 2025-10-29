package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.comment.CommentRequest;
import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.entity.comment.Comment;
import com.example.bojeung365api.entity.post.Post;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.repository.CommentRepository;
import com.example.bojeung365api.repository.PostRepository;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.util.AuthorityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<CommentResponse> getCommentResponses(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(CommentResponse::new)
                .toList();
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("comment"));
    }

    @Transactional
    public void createComment(Long postId, Long userId, CommentRequest commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("post"));
        User author = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user"));
        Comment comment = new Comment(post, author, commentRequest.body());
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequest commentRequest, String username) {
        Comment comment = getComment(commentId);
        AuthorityValidator.validateMySelf(comment.getAuthor(), username);
        comment.update(commentRequest.body());
    }

    @Transactional
    public void deleteCommentsCascade(Long postId) {
        commentRepository.deleteByPostId(postId);
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        Comment comment = getComment(commentId);
        // TODO 관리자는 열외처리할 것
        AuthorityValidator.validateMySelf(comment.getAuthor(), username);
        commentRepository.delete(comment);
    }

}
