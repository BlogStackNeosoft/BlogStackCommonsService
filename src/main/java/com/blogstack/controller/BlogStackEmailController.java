package com.blogstack.controller;

import com.blogstack.beans.responses.ServiceResponseBean;
import com.blogstack.service.IBlogStackEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${commons-service-version}/email")
public class BlogStackEmailController {

    @Autowired
    private IBlogStackEmailService blogStackEmailService;

    @PostMapping()
    public void sendMessage(@RequestParam(value = "to")String to ){
        String body = "Thank you for Signing up with BlogStack";
         blogStackEmailService.sendSignupMail(to);
    }
    @PostMapping("/otp")
    public void sendOTP(@RequestParam(value = "to") String to,@RequestParam(value = "OTP")String otp){
            blogStackEmailService.sendOTPMail(to,otp);
    }
}
