package com.example.bojeung365api.repository.notice;

import com.example.bojeung365api.dto.post.notice.NoticePostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticePostRepositoryCustom {

    Page<NoticePostListDto> findList(Pageable pageable);

}
