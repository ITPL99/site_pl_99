package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MessageDtoRequest;
import com.example.site_pl_99.dto.MessageDtoResponse;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.excaption.MessageFailedToSendException;
import com.example.site_pl_99.service.MailSenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Отправление сообщений на почту")
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

    @Operation(summary = "Отправка сообщения",description = "Отправка сообщения на почту лицея")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Сообщение отправлено"),
    @ApiResponse(responseCode = "400",description = "Некоректный запрос"),
    @ApiResponse(responseCode = "403", description = "Не достаточно прав для создания новостей")})
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

    @Operation(
            summary = "Получить все сообщения",
            description = "Возвращает все объекты MessageDtoResponse"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получены сообщения"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),})
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllMessages() throws BaseException {
        return ResponseEntity.ok((mailSenderService.getAllMessages()));
    }
    @Operation(
            summary = "Получить сообщение по ID",
            description = "Возвращает объект MessageDtoResponse по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получено сообщение"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "404", description = "Сообщение не найдена")})
    @GetMapping("/get-by-id")
    public ResponseEntity<?> getMessageById(@RequestParam Long messageId) throws BaseException {
        return ResponseEntity.ok(mailSenderService.getMessageById(messageId));
    }
    @Operation(
            summary = "Обновление сообщение по ID",
            description = "Возвращает обновлённый объект MessageDtoResponse по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное обновлено сообщение"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "404", description = "Сообщение не найдена")})
    @PutMapping("/update-by-id")
    public ResponseEntity<?> updateMessageStatus(@RequestParam Long messageId, @RequestParam String status) throws BaseException {
        return ResponseEntity.ok(mailSenderService.updateMessageStatus(messageId, status));
    }
    @Operation(
            summary = "Получить отсортированные сообщения",
            description = "Возвращает объект MessageDtoResponse"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получено сообщение"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")})
    @GetMapping("/get-all-by-status")
    public ResponseEntity<?> getAllMessagesByStatus(@RequestParam String status) throws BaseException {
        return ResponseEntity.ok(mailSenderService.getAllMessagesByStatus(status));
    }
}
