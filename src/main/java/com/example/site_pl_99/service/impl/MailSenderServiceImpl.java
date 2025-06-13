package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.MessageDtoRequest;
import com.example.site_pl_99.dto.MessageDtoResponse;
import com.example.site_pl_99.entity.MessageEntity;
import com.example.site_pl_99.entity.MessageStatus;
import com.example.site_pl_99.excaption.MessageFailedToSendException;
import com.example.site_pl_99.excaption.MessageIsNotFound;
import com.example.site_pl_99.mapper.MessageMapper;
import com.example.site_pl_99.repository.MessageRepository;
import com.example.site_pl_99.repository.MessageStatusRepository;
import com.example.site_pl_99.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    private final MessageRepository messageRepository;
    private final MessageStatusRepository messageStatusRepository;
    @Value("${spring.mail.username}")
    private String mailName;

    @Autowired
    public MailSenderServiceImpl(MessageRepository messageRepository, MessageStatusRepository messageStatusRepository) {
        this.messageRepository = messageRepository;
        this.messageStatusRepository = messageStatusRepository;
    }

    @Override
    public MessageDtoResponse sendMessage(MessageDtoRequest messageDtoRequest) {
        MessageEntity messageEntity = MessageMapper.toEntity(messageDtoRequest).setMail(mailName).setMessageStatus(messageStatusRepository.findById(3l).orElseThrow(() -> new MessageFailedToSendException("error.messageFailedToSend")));
        return MessageMapper.toDtoResponse(messageRepository.save(messageEntity));
    }

    @Override
    public List<MessageDtoResponse> getAllMessages() {
        return MessageMapper.toDtoResponseList(messageRepository.findAll());
    }

    @Override
    public MessageDtoResponse getMessageById(Long id) {
        return MessageMapper.toDtoResponse(messageRepository.findById(id).orElseThrow(() -> new MessageIsNotFound("error.messageIsNotFound")));
    }

    @Override
    public MessageDtoResponse updateMessageStatus(Long id, String status) {
        MessageEntity messageEntity = messageRepository.findById(id).orElseThrow(() -> new MessageIsNotFound("error.messageIsNotFound"));
        MessageStatus messageStatus = messageStatusRepository.findByStatusName(status).orElseThrow(() -> new MessageIsNotFound("error.status"));
        messageEntity.setMessageStatus(messageStatus).setDateUpdated(LocalDateTime.now());
        return MessageMapper.toDtoResponse(messageRepository.save(messageEntity));
    }

    @Override
    public List<MessageDtoResponse> getAllMessagesByStatus(String status) {
        MessageStatus messageStatus = messageStatusRepository.findByStatusName(status).orElseThrow(() -> new MessageIsNotFound("error.status"));
        return MessageMapper.toDtoResponseList(messageRepository.findAllByMessageStatus(messageStatus));
    }
}
