package com.blogstack.controller.advisor;

import com.blogstack.beans.responses.ServiceResponseBean;
import com.blogstack.exceptions.BlogStackCustomException;
import com.blogstack.exceptions.BlogStackDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogStackMasterRestControllerAdvice {
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(BlogStackCustomException.class)
    public ServiceResponseBean handleBlogStackCustomException(BlogStackCustomException blogStackCustomException) {
        return ServiceResponseBean.builder()
                .message(blogStackCustomException.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BlogStackDataNotFoundException.class)
    public ServiceResponseBean handleBlogStackDataNotFoundException(BlogStackDataNotFoundException blogStackDataNotFoundException) {
        return ServiceResponseBean.builder()
                .message(blogStackDataNotFoundException.getMessage())
                .build();
    }
}