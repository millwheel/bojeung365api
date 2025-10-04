package com.example.bojeung365api.repository.scam;

import com.example.bojeung365api.dto.post.scam.ScamReportPostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScamReportPostRepositoryCustom {

    Page<ScamReportPostListDto> findList(Pageable pageable);

}
