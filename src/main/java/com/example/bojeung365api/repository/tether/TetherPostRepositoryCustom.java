package com.example.bojeung365api.repository.tether;

import com.example.bojeung365api.dto.post.tether.TetherPostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TetherPostRepositoryCustom {

    Page<TetherPostListDto> findList(Pageable pageable);

}
