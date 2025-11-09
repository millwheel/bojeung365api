package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.notice.NoticePostListDto;
import com.example.bojeung365api.dto.post.notice.NoticePostRequest;
import com.example.bojeung365api.dto.post.notice.NoticePostResponse;
import com.example.bojeung365api.entity.post.NoticePost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.notice.NoticePostRepository;
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
public class NoticePostService extends AbstractPostService<
        NoticePost,
        NoticePostRequest,
        NoticePostRequest,
        NoticePostListDto,
        NoticePostResponse
        > {

    private final NoticePostRepository noticePostRepository;

    public NoticePostService(UserRepository userRepository, CommentService commentService, PostViewCountService postViewCountService, NoticePostRepository noticePostRepository) {
        super(userRepository, commentService, postViewCountService);
        this.noticePostRepository = noticePostRepository;
    }

    @Override
    protected String notFoundTarget() {
        return "noticePost";
    }

    @Override
    protected JpaRepository<NoticePost, Long> repository() {
        return noticePostRepository;
    }

    @Override
    public Page<NoticePostListDto> getPostListDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return noticePostRepository.findList(pageable);
    }

    @Override
    protected NoticePostResponse toResponse(NoticePost noticePost, List<CommentResponse> comments, boolean editable) {
        return new NoticePostResponse(noticePost, comments, editable);
    }

    @Override
    protected NoticePost createEntity(NoticePostRequest request, User author) {
        return new NoticePost(request, author);
    }

    @Override
    protected void updateEntity(NoticePost noticePost, NoticePostRequest request) {
        noticePost.update(request);
    }

}
