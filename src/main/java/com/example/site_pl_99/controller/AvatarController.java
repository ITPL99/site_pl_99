package com.example.site_pl_99.controller;

import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.WorkerService;
import com.example.site_pl_99.service.impl.AvatarWorkerServiceImpl;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

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

    @PostMapping("/create-avatar")
    public ResponseEntity<?> createAvatar(@RequestParam Long workerId, @RequestPart MultipartFile avatar) throws BaseException {
            avatarWorkerService.upload(avatar, workerId, authService.getCurrentUser());
            return ResponseEntity.ok("сохранено");
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getAvatarById(@RequestParam Long avatarId) throws BaseException {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(avatarWorkerService.getContentType(avatarId)))
                    .body(avatarWorkerService.stream(avatarId));

    }
}
