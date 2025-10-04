package com.example.bojeung365api.repository.review;

import com.example.bojeung365api.dto.post.review.ReviewPostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewPostRepositoryCustom {

    Page<ReviewPostListDto> findList(Pageable pageable);
}
