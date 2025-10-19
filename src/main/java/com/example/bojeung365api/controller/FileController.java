package com.example.bojeung365api.controller;


import com.example.bojeung365api.dto.file.FileMetaResponse;
import com.example.bojeung365api.entity.FileMeta;
import com.example.bojeung365api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public FileMetaResponse uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "category", required = false) String category
    ) {
        FileMeta fileMeta = fileService.uploadAndSave(file, category);
        return new FileMetaResponse(fileMeta);
    }
}