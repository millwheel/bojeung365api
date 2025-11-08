package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.scam.ScamReportPostListDto;
import com.example.bojeung365api.dto.post.scam.ScamReportPostRequest;
import com.example.bojeung365api.dto.post.scam.ScamReportPostResponse;
import com.example.bojeung365api.entity.post.ScamReportPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.scam.ScamReportPostRepository;
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
public class ScamReportPostService extends AbstractPostService<
        ScamReportPost,
        ScamReportPostRequest,
        ScamReportPostRequest,
        ScamReportPostListDto,
        ScamReportPostResponse
        > {

    private final ScamReportPostRepository scamReportPostRepository;

    public ScamReportPostService(UserRepository userRepository,
                                 CommentService commentService,
                                 PostViewCountService postViewCountService,
                                 ScamReportPostRepository scamReportPostRepository) {
        super(userRepository, commentService, postViewCountService);
        this.scamReportPostRepository = scamReportPostRepository;
    }

    @Override
    protected String notFoundTarget() {
        return "scamReportPost";
    }

    @Override
    protected JpaRepository<ScamReportPost, Long> repository() {
        return scamReportPostRepository;
    }

    @Override
    public Page<ScamReportPostListDto> getPostListDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return scamReportPostRepository.findList(pageable);
    }

    @Override
    protected ScamReportPostResponse toResponse(ScamReportPost scamReportPost, List<CommentResponse> comments) {
        return new ScamReportPostResponse(scamReportPost, comments);
    }

    @Override
    protected ScamReportPost createEntity(ScamReportPostRequest request, User author) {
        return new ScamReportPost(request, author);
    }

    @Override
    protected void updateEntity(ScamReportPost scamReportPost, ScamReportPostRequest request) {
        scamReportPost.update(request);
    }

}
