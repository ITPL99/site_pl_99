package com.example.site_pl_99.service;



import com.example.site_pl_99.entity.MailEntity;

import java.util.List;

public interface MailService {
    void sendMessageTo(String email,String title, String content);
    void sendReportMessage(String email,String title, String content);
    List<MailEntity> getAllMessages();
    MailEntity getMessageById(Long id);
    List<MailEntity> getMessagesByTitle(String title);
    List<MailEntity> getMessagesByStatus(String statusMessage);
    MailEntity updateMessageStatus(Long id, String statusMessage);
}
