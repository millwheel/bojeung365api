package com.example.bojeung365api.service.post;

import com.example.bojeung365api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostViewCountService {

    private final PostRepository postRepository;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseAsync(Long postId) {
        postRepository.increaseViewCount(postId);
    }
}