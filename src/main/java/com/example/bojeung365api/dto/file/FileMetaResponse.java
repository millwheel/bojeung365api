package com.example.bojeung365api.dto.file;

import com.example.bojeung365api.entity.FileMeta;
import lombok.Data;

@Data
public class FileMetaResponse {

    private Long id;

    private String uid;

    private String originalFilename;

    private long contentLength;

    private String publicUrl;

    public FileMetaResponse(FileMeta fileMeta, String publicUrl) {
        this.id = fileMeta.getId();
        this.uid = fileMeta.getUid();
        this.originalFilename = fileMeta.getOriginalFilename();
        this.contentLength = fileMeta.getContentLength();
        this.publicUrl = publicUrl;
    }
}
