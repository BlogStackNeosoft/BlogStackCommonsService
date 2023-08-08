package com.blogstack.service.impl;

import com.blogstack.beans.responses.ServiceResponseBean;
import com.blogstack.commons.BlogStackMessageConstants;
import com.blogstack.service.IBlogStackEmailService;
import com.blogstack.utils.BlogStackCommonEmailUtil;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BlogStackEmailService implements IBlogStackEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private BlogStackCommonEmailUtil blogStackCommonEmailUtil;

    private Logger LOGGER = LoggerFactory.getLogger(BlogStackEmailService.class);

    @Override
    public ServiceResponseBean sendSignupMail(String to , String firstName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom("blogstack12@gmail.com");
            message.setRecipient(MimeMessage.RecipientType.TO ,new InternetAddress(to));
            String subject = "New SignUp to BlogStack";
            message.setSubject(subject);
            message.setContent(blogStackCommonEmailUtil.getHtmlTemplate(firstName),"text/html; charset=utf-8");
            mailSender.send(message);
            return ServiceResponseBean.builder().status(Boolean.TRUE).message(BlogStackMessageConstants.INSTANCE.EMAIL_SENT_SUCCESSFULLY).build();
        }
        catch (Exception e){
                e.printStackTrace();
                return ServiceResponseBean.builder().status(Boolean.FALSE)
                        .message(BlogStackMessageConstants.INSTANCE.EMAIL_SENT_UNSUCCESSFULLY)
                        .build();


        }
    }

    public void sendOTPMail(String to , String otp){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom("blogstack12@gmail.com");
            message.setRecipient(MimeMessage.RecipientType.TO , new InternetAddress(to));
            message.setSubject("OTP");
            message.setText("Your One time passcode is :"+otp);
            mailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.warn(BlogStackMessageConstants.INSTANCE.EMAIL_SENT_UNSUCCESSFULLY);
        }
    }
}
