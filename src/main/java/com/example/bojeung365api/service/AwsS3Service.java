package com.example.bojeung365api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public String getBucketName() {
        return this.bucket;
    }

    public void upload(InputStream inputStream, String uid, String contentType, long contentLength) {
        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(bucket)
                .key(uid)
                .contentType(contentType)
                .contentLength(contentLength)
                .build();
        try {
            s3Client.putObject(putReq, RequestBody.fromInputStream(inputStream, contentLength));
        } catch (Exception e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }
    }

    public void delete(String uid) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(uid)
                .build());
    }

}
