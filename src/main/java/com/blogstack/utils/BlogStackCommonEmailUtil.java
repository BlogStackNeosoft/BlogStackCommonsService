package com.blogstack.utils;

import org.springframework.stereotype.Component;

@Component
public class BlogStackCommonEmailUtil {

    public String getHtmlTemplate(String firstName){
        String htmlTemplate="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Welcome to BlogStack</title>\n" +
                "    <style>\n" +
                "        /* Add your custom styles here */\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            line-height: 1.6;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f7f7f7;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #ffffff;\n" +
                "            border: 1px solid #e1e1e1;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .logo {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .logo img {\n" +
                "            max-width: 150px;\n" +
                "        }\n" +
                "        .welcome-text {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .cta-button {\n" +
                "            display: block;\n" +
                "            width: 100%;\n" +
                "            max-width: 200px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 10px 20px;\n" +
                "            text-align: center;\n" +
                "            background-color: #007bff;\n" +
                "            color: #ffffff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        .cta-button:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"logo\">\n" +
                "            <span style=\"color: black; font-family: monospace;\">Blog</span><span style=\"color: red;font-family: monospace;\">Stack</span>\n" +
                "        </div>\n" +
                "        <div class=\"welcome-text\">\n" +
                "            <h1>Hello , "+firstName+" !</h1>\n"+
                "            <h1>Welcome to BlogStack!</h1>\n" +
                "            <p>Thank you for registering on BlogStack. We are excited to have you on board.</p>\n" +
                "            <p>Start sharing your thoughts, experiences, and knowledge with the community!</p>\n" +
                "        </div>\n" +
                "        <div class=\"cta-button-container\">\n" +
                "            <a class=\"cta-button\" href=\"http://localhost:4200\">Get Started</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        return htmlTemplate;
    }
}
