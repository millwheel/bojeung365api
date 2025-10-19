package com.example.bojeung365api.service;

import com.example.bojeung365api.entity.FileMeta;
import com.example.bojeung365api.repository.FileMetaRepository;
import com.example.bojeung365api.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final AwsS3Service awsS3Service;
    private final FileMetaRepository storedFileRepository;

    public void getFileMeta(){

    }

    @Transactional
    public FileMeta uploadAndSave(MultipartFile file, String category) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("빈 파일은 업로드할 수 없습니다.");
        }
        String originalFileName = Objects.requireNonNull(file.getOriginalFilename());
        String contentType = (file.getContentType() != null && !file.getContentType().isBlank())
                ? file.getContentType()
                : "application/octet-stream";
        long contentLength = file.getSize();
        String ext = FileUtils.extractExtension(originalFileName);
        String uid = buildUid(category, ext);
        String bucketName = awsS3Service.getBucketName();
        FileMeta fileMeta = new FileMeta(uid, originalFileName, contentType, contentLength, bucketName);
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Input Stream 읽기 실패");
        }
        awsS3Service.upload(inputStream, uid, contentType, contentLength);
        return storedFileRepository.save(fileMeta);
    }

    private String buildUid(String category, String ext) {
        LocalDate today = LocalDate.now();
        String datePath = "%04d/%02d/%02d".formatted(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        String uuid = UUID.randomUUID().toString();
        String prefix = (category == null || category.isBlank()) ? "" : category.replaceAll("^/|/$", "") + "/";
        return prefix + datePath + "/" + uuid + ext;
    }

    @Transactional
    public void deleteById(Long id) {
        FileMeta f = storedFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("파일을 찾을 수 없습니다. id=" + id));
        awsS3Service.delete(f.getUid());
        storedFileRepository.delete(f);
    }

}
