package com.csu.rules.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by ltaoj on 2017/9/20.
 */
@Configuration
public class EmailConfig {

    @Bean
    public MailSender mailSender(Environment environment) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setPort(25);
        mailSender.setUsername("taoj0417@163.com");
        mailSender.setPassword("helloworld123");
        return mailSender;
    }
}