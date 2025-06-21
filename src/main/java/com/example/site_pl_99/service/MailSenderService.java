package com.example.site_pl_99.service;



import jakarta.activation.FileTypeMap;

import java.io.File;
import java.util.List;
public interface MailSenderService {


    void sendMessage(String email, String title, String content, FileTypeMap... files);
}
