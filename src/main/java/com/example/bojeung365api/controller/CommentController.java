package com.example.bojeung365api.controller;

import com.example.bojeung365api.dto.comment.CommentRequest;
import com.example.bojeung365api.security.dto.CustomUserDetails;
import com.example.bojeung365api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestParam Long postId,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody CommentRequest commentRequest) {
        commentService.createComment(postId, userDetails.getUsername(), commentRequest);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(commentId, commentRequest, userDetails.getUsername());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(commentId, userDetails.getUsername());
    }

}
