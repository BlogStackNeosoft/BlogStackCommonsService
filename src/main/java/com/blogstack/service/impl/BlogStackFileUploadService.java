package com.blogstack.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.blogstack.commons.BlogStackCommonConstants;
import com.blogstack.service.IBlogStackFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class BlogStackFileUploadService implements IBlogStackFileUploadService {

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public ResponseEntity<File> convertMultiPartFileToFile(MultipartFile blogStackBlogImage) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = null;
        File convertedFile = null;
        try {
            convertedFile = new File(Objects.requireNonNull(blogStackBlogImage.getOriginalFilename()));
            fileOutputStream = new FileOutputStream(convertedFile);
            fileOutputStream.write(blogStackBlogImage.getBytes());
        } finally {
            assert fileOutputStream != null;
            fileOutputStream.close();
        }
        return ResponseEntity.ok(convertedFile);
    }

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile blogStackProfilePhoto,String bucketName) throws IOException {
        ResponseEntity<File> convertedFile = convertMultiPartFileToFile(blogStackProfilePhoto);
        String fileName = System.currentTimeMillis() + BlogStackCommonConstants.INSTANCE.UNDERSCORE_STRING + blogStackProfilePhoto.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, convertedFile.getBody()));
        URL url = s3Client.getUrl(bucketName, fileName);
        return ResponseEntity.ok(url.toString());
    }

//    @Override
//    public ResponseEntity<String> uploadFile(MultipartFile blogStackProfilePhoto) throws IOException {
//        ResponseEntity<File> convertedFile = convertMultiPartFileToFile(blogStackProfilePhoto);
//        String fileName = System.currentTimeMillis() + BlogStackCommonConstants.INSTANCE.UNDERSCORE_STRING + blogStackProfilePhoto.getOriginalFilename();
//        s3Client.putObject(new PutObjectRequest(bucketNameProfile, fileName, convertedFile.getBody()));
//        URL url = s3Client.getUrl(bucketNameProfile, fileName);
//        return ResponseEntity.ok(url.toString());
//    }

}