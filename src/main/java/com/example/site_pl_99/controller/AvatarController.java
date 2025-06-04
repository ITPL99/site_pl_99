package com.example.site_pl_99.controller;

import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.WorkerService;
import com.example.site_pl_99.service.impl.AvatarWorkerServiceImpl;
import com.example.site_pl_99.utils.Internalization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;
@Tag(name = "Контроллер Аватара")
@RestController
@RequestMapping("/api/avatars")
public class AvatarController {
    private final AvatarWorkerServiceImpl avatarWorkerService;
    private final AuthService authService;
    private final WorkerService workerService;
    private final Internalization internalization;

    @Autowired
    public AvatarController(AvatarWorkerServiceImpl avatarWorkerService, AuthService authService, WorkerService workerService, Internalization internalization) {
        this.avatarWorkerService = avatarWorkerService;
        this.authService = authService;
        this.workerService = workerService;
        this.internalization = internalization;
    }
    @Operation(
            summary = "Создать аватар",
            description ="Загружает и сохраняет работника по указанному ID, фотка не может весить больше 3 мб "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аватар сохранен"),
            @ApiResponse(responseCode = "409",description = "Ресурс уже существует и не может быть дубликатов"),
            @ApiResponse(responseCode = "413", description = "Слишком большой размер файла"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав для созданий аватара")})

    @PostMapping("/create-avatar")
    public ResponseEntity<?> createAvatar(@Parameter(description = "Принимает Id любого работника") @RequestParam Long workerId,
                                          @Parameter(description = "принимает фото нашего рабочего") @RequestPart MultipartFile avatar) throws BaseException {
            avatarWorkerService.upload(avatar, workerId, authService.getCurrentUser());
            return ResponseEntity.ok("сохранено");
    }
    @Operation(summary = "Получить аватар по его Id",
    description = "Возвращает Аватар по указанному Id")
    @ApiResponses(value =
                    {@ApiResponse(responseCode = "200",description = "Успешно возвращен"),
                    @ApiResponse(responseCode = "400", description = "Некоректный запрос"),
                    @ApiResponse(responseCode = "404", description = "Не найден")})

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getAvatarById(@Parameter(description = "Id аватара") @RequestParam Long avatarId) throws BaseException {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(avatarWorkerService.getContentType(avatarId)))
                    .body(avatarWorkerService.stream(avatarId));

    }
}
