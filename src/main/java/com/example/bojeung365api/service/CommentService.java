package com.example.bojeung365api.service;

import com.example.bojeung365api.entity.comment.Comment;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.exception.custom.InvalidAuthorityException;
import com.example.bojeung365api.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().sorted(Comparator.comparing(Comment::getCreatedAt)).toList();
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("comment"));
    }

    public void createComment() {

    }

    public void updateComment(Long commentId) {
        Comment comment = getComment(commentId);
    }

    public void deleteComment(Long commentId, Long requestorId) {
        Comment comment = getComment(commentId);
        if (!comment.getAuthor().getId().equals(requestorId)) {
            throw new InvalidAuthorityException();
        }
        commentRepository.delete(comment);
    }

}
