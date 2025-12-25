package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.comment.CommentRequest;
import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.entity.comment.Comment;
import com.example.bojeung365api.entity.post.Post;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.exception.custom.UserNotFoundException;
import com.example.bojeung365api.repository.CommentRepository;
import com.example.bojeung365api.repository.PostRepository;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.util.AuthorityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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

    public List<CommentResponse> getCommentResponses(Long postId, UserDetails userDetails) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(comment -> {
                    boolean editable = comment.getAuthor().getUsername().equals(userDetails.getUsername());
                    return new CommentResponse(comment, editable);
                })
                .toList();
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("comment"));
    }

    @Transactional
    public void createComment(Long postId, String username, CommentRequest commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("post"));
        User author = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        Comment comment = new Comment(post, author, commentRequest.body());
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequest commentRequest, String username) {
        Comment comment = getComment(commentId);
        User requestUser = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        AuthorityValidator.validateEditable(comment.getAuthor(), requestUser);
        comment.update(commentRequest.body());
    }

    @Transactional
    public void deleteCommentsCascade(Long postId) {
        commentRepository.deleteByPostId(postId);
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        Comment comment = getComment(commentId);
        User requestUser = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        AuthorityValidator.validateEditable(comment.getAuthor(), requestUser);
        commentRepository.delete(comment);
    }

}
