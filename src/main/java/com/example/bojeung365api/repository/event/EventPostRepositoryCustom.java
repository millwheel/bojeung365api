package com.example.bojeung365api.repository.event;

import com.example.bojeung365api.dto.post.event.EventPostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventPostRepositoryCustom {

    Page<EventPostListDto> findList(Pageable pageable);

}
