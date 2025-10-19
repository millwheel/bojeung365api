package com.example.bojeung365api.dto.file;

import com.example.bojeung365api.entity.FileMeta;
import lombok.Data;

@Data
public class FileMetaResponse {

    private Long id;

    private String uid;

    private String originalFilename;

    private long contentLength;

    public FileMetaResponse(FileMeta fileMeta) {
        this.id = fileMeta.getId();
        this.uid = fileMeta.getUid();
        this.originalFilename = fileMeta.getOriginalFilename();
        this.contentLength = fileMeta.getContentLength();
    }
}
