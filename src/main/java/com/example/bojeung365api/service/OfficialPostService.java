package com.example.bojeung365api.service;

import com.example.bojeung365api.dto.post.OfficialPostListDto;
import com.example.bojeung365api.repository.official.OfficialPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficialPostService {

    private final OfficialPostRepository officialPostRepository;

    public Page<OfficialPostListDto> getPages(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return officialPostRepository.findList(pageable);
    }

}
