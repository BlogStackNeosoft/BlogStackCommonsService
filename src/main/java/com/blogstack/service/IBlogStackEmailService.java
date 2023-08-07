package com.blogstack.service;

import com.blogstack.beans.responses.ServiceResponseBean;

public interface IBlogStackEmailService {

    ServiceResponseBean sendSignupMail(String to , String firstName);

    void sendOTPMail(String to , String otp);
}
