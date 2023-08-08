package com.blogstack.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IBlogStackFileUploadService {
    ResponseEntity<File> convertMultiPartFileToFile(MultipartFile file) throws FileNotFoundException, IOException;

    ResponseEntity<String> uploadFile(MultipartFile file, String bucketName) throws IOException;
}