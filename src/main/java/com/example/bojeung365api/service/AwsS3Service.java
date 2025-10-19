package com.example.bojeung365api.service;

import com.example.bojeung365api.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file, String dir) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("빈 파일은 업로드할 수 없습니다.");
        }

        String contentType = (file.getContentType() != null && !file.getContentType().isBlank())
                ? file.getContentType()
                : "application/octet-stream";
        long contentLength = file.getSize();

        String original = file.getOriginalFilename();
        String ext = FileUtils.extractExtension(original);
        String key = buildKeyWithDateAndUuid(dir, ext);

        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .contentLength(contentLength)
                .build();

        try {
            s3Client.putObject(putReq, RequestBody.fromInputStream(file.getInputStream(), contentLength));
        } catch (IOException e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }

        var url = s3Client.utilities().getUrl(b -> b.bucket(bucket).key(key));
        return url.toString();
    }

    public void delete(String objectKey) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(objectKey)
                .build());
    }

    private String buildKeyWithDateAndUuid(String dir, String ext) {
        LocalDate today = LocalDate.now();
        String datePath = "%04d/%02d/%02d".formatted(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        String uuid = UUID.randomUUID().toString();

        String mid = (dir == null || dir.isBlank()) ? "" : dir.replaceAll("^/|/$", "") + "/";

        return mid + datePath + "/" + uuid + ext;
    }

}
