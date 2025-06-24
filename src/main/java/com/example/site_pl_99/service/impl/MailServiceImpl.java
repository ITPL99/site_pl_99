package com.example.site_pl_99.service.impl;


import com.example.site_pl_99.entity.MailEntity;
import com.example.site_pl_99.enums.StatusMessage;
import com.example.site_pl_99.excaption.MassageSendException;
import com.example.site_pl_99.excaption.MessageIsNotFoundException;
import com.example.site_pl_99.excaption.NoSuchMessageStatusException;
import com.example.site_pl_99.repository.MailRepository;
import com.example.site_pl_99.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String mailName;

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, MailRepository mailRepository) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
    }



    @Override
    public void sendMessage(String email,String title, String content){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(mailName);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
            MailEntity mailEntity = new MailEntity().setTitle(title).setContent(content).setEmailTo(mailName);
            mailRepository.save(mailEntity);
        }catch (MessagingException e) {
            log.error(e.getMessage());
            throw new MassageSendException("error.mailSend");
        }
    }

    @Override
    public List<MailEntity> getAllMessages() {
        return mailRepository.findAll();
    }

    @Override
    public MailEntity getMessageById(Long id) {
        return mailRepository.findById(id).orElseThrow(() -> new MessageIsNotFoundException("error.messageIsNotFound"));
    }

    @Override
    public List<MailEntity> getMessagesByTitle(String title) {
        return mailRepository.findByTitle(title);
    }

    @Override
    public List<MailEntity> getMessagesByStatus(String statusMessage) {
        return mailRepository.findByStatusMail(StatusMessage.valueOf(statusMessage));
    }

    @Override
    public MailEntity updateMessageStatus(Long id, String statusMessage) {
        try {
            MailEntity mailEntity = mailRepository.findById(id).orElseThrow(() -> new MessageIsNotFoundException("error.messageIsNotFound"));
            StatusMessage status = StatusMessage.valueOf(statusMessage);
            mailEntity.setStatusMail(status);
            return mailRepository.save(mailEntity);
        }catch (IllegalArgumentException e) {
            throw new NoSuchMessageStatusException("error.status");
        }
    }


}
