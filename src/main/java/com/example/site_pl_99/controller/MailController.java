package com.example.site_pl_99.controller;

import com.example.site_pl_99.entity.MailEntity;
import com.example.site_pl_99.service.MailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
@SecurityRequirement(name = "bearerAuth")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(@RequestParam String title, @RequestParam String content) {
        mailService.sendMessage(title, content);
        return ResponseEntity.ok("success.messageSend");
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<MailEntity>> getAll() {
        return ResponseEntity.ok(mailService.getAllMessages());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MailEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mailService.getMessageById(id));
    }

    @GetMapping("/get-by-title")
    public ResponseEntity<List<MailEntity>> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(mailService.getMessagesByTitle(title));
    }

    @GetMapping("/get-by-status")
    public ResponseEntity<List<MailEntity>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(mailService.getMessagesByStatus(status));
    }

    @PutMapping("/update-status-by-id")
    public ResponseEntity<?> updateStatusById(@RequestParam Long id, @RequestParam String status) {
        return ResponseEntity.ok(mailService.updateMessageStatus(id, status));
    }
}
