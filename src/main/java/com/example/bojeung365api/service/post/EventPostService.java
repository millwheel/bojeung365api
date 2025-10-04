package com.example.bojeung365api.service.post;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.event.EventPostListDto;
import com.example.bojeung365api.dto.post.event.EventPostRequest;
import com.example.bojeung365api.dto.post.event.EventPostResponse;
import com.example.bojeung365api.entity.post.EventPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.event.EventPostRepository;
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
public class EventPostService extends AbstractPostService<
        EventPost,
        EventPostRequest,
        EventPostRequest,
        EventPostListDto,
        EventPostResponse
        > {

    private final EventPostRepository eventPostRepository;

    public EventPostService(UserRepository userRepository, CommentService commentService, EventPostRepository eventPostRepository) {
        super(userRepository, commentService);
        this.eventPostRepository = eventPostRepository;
    }

    @Override
    protected String notFoundTarget() {
        return "eventPost";
    }

    @Override
    protected JpaRepository<EventPost, Long> repository() {
        return eventPostRepository;
    }

    @Override
    public Page<EventPostListDto> getBoard(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return eventPostRepository.findList(pageable);
    }

    @Override
    protected EventPostResponse toResponse(EventPost eventPost, List<CommentResponse> comments) {
        return new EventPostResponse(eventPost, comments);
    }

    @Override
    protected EventPost createEntity(EventPostRequest request, User author) {
        return new EventPost(request, author);
    }

    @Override
    protected void updateEntity(EventPost eventPost, EventPostRequest request) {
        eventPost.update(request);
    }

}
