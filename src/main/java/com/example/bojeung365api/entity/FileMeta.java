package com.example.bojeung365api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stored_file")
public class FileMeta extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** S3 object key (예: dir/2025/10/19/uuid.png) */
    @Column(nullable = false, unique = true, length = 1024)
    private String uid;

    @Column(nullable = false)
    private String originalFilename;

    @Column(nullable = false, length = 100)
    private String contentType;

    @Column(nullable = false)
    private long contentLength;

    @Column(nullable = false)
    private String bucket;

    public FileMeta(String uid, String originalFilename, String contentType, long contentLength, String bucket) {
        this.uid = uid;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.bucket = bucket;
    }
}