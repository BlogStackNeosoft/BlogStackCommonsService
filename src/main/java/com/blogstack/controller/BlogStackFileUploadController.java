package com.blogstack.controller;

import com.blogstack.service.IBlogStackFileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("${commons-service-version}/file-upload")
@AllArgsConstructor
public class BlogStackFileUploadController {

    private final IBlogStackFileUploadService blogStackFileUploadService;

    @PutMapping("/")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "bucketName") String bucketName) throws IOException {
        return this.blogStackFileUploadService.uploadFile(file, bucketName);
    }
}