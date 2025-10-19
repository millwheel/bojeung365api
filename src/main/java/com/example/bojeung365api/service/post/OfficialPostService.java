package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.official.OfficialPostRequest;
import com.example.bojeung365api.dto.post.official.OfficialPostListDto;
import com.example.bojeung365api.dto.post.official.OfficialPostResponse;
import com.example.bojeung365api.entity.post.OfficialPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.official.OfficialPostRepository;
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
public class OfficialPostService extends AbstractPostService<
        OfficialPost,
        OfficialPostRequest,
        OfficialPostRequest,
        OfficialPostListDto,
        OfficialPostResponse
        > {

    private final OfficialPostRepository officialPostRepository;

    public OfficialPostService(UserRepository userRepository, CommentService commentService, OfficialPostRepository officialPostRepository) {
        super(userRepository, commentService);
        this.officialPostRepository = officialPostRepository;
    }

    @Override
    protected String notFoundTarget() {
        return "officialPost";
    }

    @Override
    protected JpaRepository<OfficialPost, Long> repository() {
        return officialPostRepository;
    }

    @Override
    public Page<OfficialPostListDto> getPostListDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return officialPostRepository.findList(pageable);
    }

    @Override
    protected OfficialPostResponse toResponse(OfficialPost officialPost, List<CommentResponse> comments) {
        return new OfficialPostResponse(officialPost, comments);
    }

    @Override
    protected OfficialPost createEntity(OfficialPostRequest request, User author) {
        return new OfficialPost(request, author);
    }

    @Override
    protected void updateEntity(OfficialPost officialPost, OfficialPostRequest request) {
        officialPost.update(request);
    }

}
