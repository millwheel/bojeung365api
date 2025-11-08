package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.tether.TetherPostListDto;
import com.example.bojeung365api.dto.post.tether.TetherPostRequest;
import com.example.bojeung365api.dto.post.tether.TetherPostResponse;
import com.example.bojeung365api.entity.post.TetherPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.tether.TetherPostRepository;
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
public class TetherPostService extends AbstractPostService<
        TetherPost,
        TetherPostRequest,
        TetherPostRequest,
        TetherPostListDto,
        TetherPostResponse
        > {

    private final TetherPostRepository tetherPostRepository;

    public TetherPostService(UserRepository userRepository,
                             CommentService commentService,
                             PostViewCountService postViewCountService,
                             TetherPostRepository tetherPostRepository) {
        super(userRepository, commentService, postViewCountService);
        this.tetherPostRepository = tetherPostRepository;
    }

    @Override
    protected String notFoundTarget() {
        return "tetherPost";
    }

    @Override
    protected JpaRepository<TetherPost, Long> repository() {
        return tetherPostRepository;
    }

    @Override
    public Page<TetherPostListDto> getPostListDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tetherPostRepository.findList(pageable);
    }

    @Override
    protected TetherPostResponse toResponse(TetherPost tetherPost, List<CommentResponse> comments, boolean editable) {
        return new TetherPostResponse(tetherPost, comments);
    }

    @Override
    protected TetherPost createEntity(TetherPostRequest request, User author) {
        return new TetherPost(request, author);
    }

    @Override
    protected void updateEntity(TetherPost tetherPost, TetherPostRequest request) {
        tetherPost.update(request);
    }

}
