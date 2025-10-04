package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.comment.CommentResponse;
import com.example.bojeung365api.dto.post.official.OfficialPostCreateRequest;
import com.example.bojeung365api.dto.post.official.OfficialPostListDto;
import com.example.bojeung365api.dto.post.official.OfficialPostResponse;
import com.example.bojeung365api.dto.post.official.OfficialPostUpdateRequest;
import com.example.bojeung365api.entity.post.OfficialPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.official.OfficialPostRepository;
import com.example.bojeung365api.util.AuthorityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfficialPostService {

    private final OfficialPostRepository officialPostRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    public Page<OfficialPostListDto> getBoard(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return officialPostRepository.findList(pageable);
    }

    public OfficialPostResponse getPostResponse(Long postId) {
        OfficialPost officialPost = getPost(postId);
        officialPost.increaseViewCount();
        List<CommentResponse> commentResponses = commentService.getCommentResponses(postId);
        return new OfficialPostResponse(officialPost, commentResponses);
    }

    private OfficialPost getPost(Long postId) {
        return officialPostRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("official post"));
    }

    @Transactional
    public void createPage(OfficialPostCreateRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new DataNotFoundException("user"));
        OfficialPost officialPost = new OfficialPost(request, author);
        officialPostRepository.save(officialPost);
    }

    @Transactional
    public void updatePage(Long postId, OfficialPostUpdateRequest request) {
        OfficialPost officialPost = getPost(postId);
        officialPost.update(request);
    }

    @Transactional
    public void deletePage(Long postId, Long requestorId) {
        OfficialPost officialPost = getPost(postId);
        // TODO 관리자는 열외처리할 것
        AuthorityValidator.validateMySelf(officialPost.getAuthor(), requestorId);
        commentService.deleteCommentsCascade(postId);
        officialPostRepository.delete(officialPost);
    }

}
