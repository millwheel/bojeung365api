package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.post.official.OfficialPostCreateRequest;
import com.example.bojeung365api.dto.post.official.OfficialPostListDto;
import com.example.bojeung365api.dto.post.official.OfficialPostResponse;
import com.example.bojeung365api.entity.post.OfficialPost;
import com.example.bojeung365api.entity.user.User;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
import com.example.bojeung365api.repository.UserRepository;
import com.example.bojeung365api.repository.official.OfficialPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OfficialPostService {

    private final OfficialPostRepository officialPostRepository;
    private final UserRepository userRepository;

    public Page<OfficialPostListDto> getPages(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return officialPostRepository.findList(pageable);
    }

    public OfficialPostResponse getPage(Long postId) {
        OfficialPost officialPost = officialPostRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("official post"));
        officialPost.increaseViewCount();
        return new OfficialPostResponse(officialPost);
    }

    public void createPage(OfficialPostCreateRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new DataNotFoundException("user"));
        OfficialPost officialPost = new OfficialPost(request, author);
        officialPostRepository.save(officialPost);
    }

    public void updatePage(Long postId) {
        OfficialPost officialPost = officialPostRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("official post"));
        // TODO update 로직
    }

    public void deletePage(Long postId) {
        OfficialPost officialPost = officialPostRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("official post"));
        // TODO cascade comment delete
        officialPostRepository.delete(officialPost);
    }

}
