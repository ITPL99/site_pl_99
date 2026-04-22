package com.example.site_pl_99.controller;

import com.example.site_pl_99.entity.MailEntity;
import com.example.site_pl_99.service.MailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Контроллер для управления почтовыми сообщениями.
 * <p>
 * Предоставляет API для отправки, получения и управления email сообщениями.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/mail")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Почта",
        description = """
                API для управления почтовыми сообщениями.
                
                **Возможности:**
                - Отправка email сообщений
                - Получение списка сообщений
                - Управление статусами сообщений
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @Operation(
            summary = "Отправить сообщение",
            description = "Отправляет сообщение на указанный email. Ответ возвращается с учетом текущей локали пользователя.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сообщение отправлено")
            }
    )
    @PostMapping("/send-message")
    public String sendReportMessage(
            @Parameter(description = "Email получателя", example = "user@example.com") @RequestParam String email,
            @Parameter(description = "Заголовок письма", example = "Report") @RequestParam String title,
            @Parameter(description = "Содержимое письма", example = "This is a report message") @RequestParam String content) {

        mailService.sendReportMessage(email, title, content);
        Locale locale = LocaleContextHolder.getLocale();

        return locale.getLanguage().equals("ru")? "Сообщение отправленно":
                locale.getLanguage().equals("kg")? "Сообщение жөнөтүлдү":
                        "Message sent successfully";
    }

    @Operation(
            summary = "Получить все сообщения",
            description = "Возвращает список всех email-сообщений",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получено все сообщения")
            }
    )
    @GetMapping("/get-all")
    public ResponseEntity<List<MailEntity>> getAll() {
        return ResponseEntity.ok(mailService.getAllMessages());
    }

    @Operation(
            summary = "Получить сообщение по ID",
            description = "Возвращает сообщение по указанному ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получено сообщение по ID")
            }
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MailEntity> getById(
            @Parameter(description = "ID сообщения", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(mailService.getMessageById(id));
    }

    @Operation(
            summary = "Получить сообщения по заголовку",
            description = "Возвращает список сообщений с указанным заголовком",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получено сообщения по заголовку")
            }
    )
    @GetMapping("/get-by-title")
    public ResponseEntity<List<MailEntity>> getByTitle(
            @Parameter(description = "Заголовок сообщения", example = "Monthly Report") @RequestParam String title) {
        return ResponseEntity.ok(mailService.getMessagesByTitle(title));
    }

    @Operation(
            summary = "Получить сообщения по статусу",
            description = "Возвращает список сообщений с указанным статусом",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Получено сообщения по статусу")
            }

    )
    @GetMapping("/get-by-status")
    public ResponseEntity<List<MailEntity>> getByStatus(
            @Parameter(description = "Статус сообщения", example = "SENT") @RequestParam String status) {
        return ResponseEntity.ok(mailService.getMessagesByStatus(status));
    }

    @Operation(
            summary = "Обновить статус сообщения по ID",
            description = "Обновляет статус сообщения (например, на 'SENT' или 'FAILED') по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Обновнавлен статус сообщения по ID")
            }
    )
    @PutMapping("/update-status-by-id")
    public ResponseEntity<?> updateStatusById(
            @Parameter(description = "ID сообщения", example = "5") @RequestParam Long id,
            @Parameter(description = "Новый статус", example = "READ") @RequestParam String status) {
        return ResponseEntity.ok(mailService.updateMessageStatus(id, status));
    }
}
