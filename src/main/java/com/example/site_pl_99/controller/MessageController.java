package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MessageDtoRequest;
import com.example.site_pl_99.dto.MessageDtoResponse;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.excaption.MessageFailedToSendException;
import com.example.site_pl_99.service.MailSenderService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail-sender")
public class MessageController {
    private final MailSenderService mailSenderService;
    private final JavaMailSender javaMailSender;

    @Autowired
    public MessageController(MailSenderService mailSenderService, JavaMailSender javaMailSender) {
        this.mailSenderService = mailSenderService;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDtoRequest messageDtoRequest) throws BaseException {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            MessageDtoResponse messageDtoResponse = mailSenderService.sendMessage(messageDtoRequest);
            mimeMessageHelper.setFrom(messageDtoResponse.getMailName());
            mimeMessageHelper.setTo(messageDtoResponse.getMailName());
            mimeMessageHelper.setSubject(messageDtoResponse.getTitle());
            mimeMessageHelper.setText(messageDtoResponse.getMessage());
            javaMailSender.send(mimeMessage);
            return ResponseEntity.ok(messageDtoResponse);
        }catch (Exception e){
            e.printStackTrace();
            throw new MessageFailedToSendException("error.messageSend");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllMessages() throws BaseException {
        return ResponseEntity.ok((mailSenderService.getAllMessages()));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getMessageById(@RequestParam Long messageId) throws BaseException {
        return ResponseEntity.ok(mailSenderService.getMessageById(messageId));
    }
}
