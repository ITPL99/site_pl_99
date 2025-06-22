package com.example.site_pl_99.service;



import jakarta.activation.FileTypeMap;

public interface MailSenderService {


    void sendMessage(String email, String title, String content, FileTypeMap... files);
}
