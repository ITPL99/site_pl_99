package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.MessageDtoRequest;
import com.example.site_pl_99.dto.MessageDtoResponse;
import com.example.site_pl_99.entity.MailEntity;
import com.example.site_pl_99.excaption.MailIsNotFoundException;
import com.example.site_pl_99.excaption.MessageIsNotFound;
import com.example.site_pl_99.mapper.MessageMapper;
import com.example.site_pl_99.repository.MailRepository;
import com.example.site_pl_99.repository.MessageRepository;
import com.example.site_pl_99.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    private final MessageRepository messageRepository;
    private final MailRepository mailRepository;
    @Value("${spring.mail.username}")
    private String mailName;

    @Autowired
    public MailSenderServiceImpl(MessageRepository messageRepository, MailRepository mailRepository) {
        this.messageRepository = messageRepository;
        this.mailRepository = mailRepository;
    }

    @Override
    public MessageDtoResponse sendMessage(MessageDtoRequest messageDtoRequest) {
        MailEntity mailEntity = mailRepository.findByMailName(mailName).orElseThrow(() -> new MailIsNotFoundException("error.mailIsNotFound"));
        return MessageMapper.toDtoResponse(messageRepository.save(MessageMapper.toEntity(messageDtoRequest).setMailEntity(mailEntity)));
    }

    @Override
    public List<MessageDtoResponse> getAllMessages() {
        return MessageMapper.toDtoResponseList(messageRepository.findAll());
    }

    @Override
    public MessageDtoResponse getMessageById(Long id) {
        return MessageMapper.toDtoResponse(messageRepository.findById(id).orElseThrow(() -> new MessageIsNotFound("error.messageIsNotFound")));
    }
}
