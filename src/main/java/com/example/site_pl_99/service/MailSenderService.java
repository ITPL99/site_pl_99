package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.MessageDtoRequest;
import com.example.site_pl_99.dto.MessageDtoResponse;

import java.util.List;

public interface MailSenderService {
    public MessageDtoResponse sendMessage(MessageDtoRequest messageDtoRequest);
    public List<MessageDtoResponse> getAllMessages();
    public MessageDtoResponse getMessageById(Long id);
}
