package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.post.OfficialPostCreateRequest;
import com.example.bojeung365api.dto.post.OfficialPostListDto;
import com.example.bojeung365api.exception.custom.DataNotFoundException;
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

    public Page<OfficialPostListDto> getPages(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return officialPostRepository.findList(pageable);
    }

    public void getPage(Long postId) {
        officialPostRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("official post"));
    }

    public void createPage(OfficialPostCreateRequest request) {

    }

    public void updatePage() {

    }

    public void deletePage() {

    }

}
