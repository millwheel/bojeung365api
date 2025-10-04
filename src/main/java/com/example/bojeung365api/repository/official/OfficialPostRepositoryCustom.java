package com.example.bojeung365api.repository.official;

import com.example.bojeung365api.dto.post.OfficialPostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfficialPostRepositoryCustom {

    Page<OfficialPostListDto> findList(Pageable pageable);

}
