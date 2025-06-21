package com.example.site_pl_99.service.impl;


import com.example.site_pl_99.service.MailSenderService;
import jakarta.activation.FileTypeMap;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Value("${spring.mail.username}")
    private String mailName;

    private final JavaMailSender mailSender;

    public MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    @Override
    public void sendMessage(String email, String title, String content, FileTypeMap... files) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(mailName);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(title);
            if(files != null && files.length > 0) {
                for (FileTypeMap fileTypeMap : files)
                    mimeMessageHelper.setFileTypeMap(fileTypeMap);
            }
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
        }catch (MessagingException e) {
            log.error(e.getMessage());
        }


    }


}
