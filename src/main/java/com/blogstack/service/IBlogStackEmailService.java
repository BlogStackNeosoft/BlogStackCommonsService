package com.blogstack.service;

import com.blogstack.beans.responses.ServiceResponseBean;

public interface IBlogStackEmailService {

    ServiceResponseBean sendSignupMail(String to );

    void sendOTPMail(String to , String otp);
}
