package com.example.bojeung365api.controller;

import com.example.bojeung365api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestParam Long postId) {

    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) {

    }

}
