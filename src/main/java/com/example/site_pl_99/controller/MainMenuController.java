package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MainMenuDtoRequest;
import com.example.site_pl_99.dto.MainMenuDtoResponse;
import com.example.site_pl_99.mapper.MainMenuMapper;
import com.example.site_pl_99.service.MainMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/main-menu")
public class MainMenuController {

    private final MainMenuService mainMenuService;

    @Autowired
    public MainMenuController(MainMenuService mainMenuService) {
        this.mainMenuService = mainMenuService;
    }

    @Operation(summary = "Добавление нового элемента главного меню")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Главное меню успешно добавлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MainMenuDtoResponse.class)))
    })
    @PostMapping("/add-main-menu")
    public ResponseEntity<MainMenuDtoResponse> addMainMenu(
            @RequestBody(description = "Данные главного меню для добавления", required = true,
                    content = @Content(schema = @Schema(implementation = MainMenuDtoRequest.class)))
            @org.springframework.web.bind.annotation.RequestBody MainMenuDtoRequest mainMenuDtoRequest) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(
                mainMenuService.addMainMenu(MainMenuMapper.toMainMenuEntity(mainMenuDtoRequest))));
    }

    @Operation(summary = "Получить элемент главного меню по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Главное меню найдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MainMenuDtoResponse.class)))
    })
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MainMenuDtoResponse> getMainMenuById(
            @PathVariable Long id) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(mainMenuService.getMainMenuById(id)));
    }

    @Operation(summary = "Обновить элемент главного меню по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Главное меню успешно обновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MainMenuDtoResponse.class)))
    })
    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<MainMenuDtoResponse> updateMainMenu(
            @RequestBody(description = "Новые данные главного меню", required = true,
                    content = @Content(schema = @Schema(implementation = MainMenuDtoRequest.class)))
            @org.springframework.web.bind.annotation.RequestBody MainMenuDtoRequest mainMenuDtoRequest,
            @PathVariable Long id) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(
                mainMenuService.updateMainMenuById(id, MainMenuMapper.toMainMenuEntity(mainMenuDtoRequest))));
    }

    @Operation(summary = "Удалить элемент главного меню по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Главное меню успешно удалено", content = @Content)
    })
    @DeleteMapping("/delete-by-id/{id}")
    public String deleteMainMenuById(@PathVariable Long id) {
        mainMenuService.deleteMainMenuById(id);
        Locale locale = LocaleContextHolder.getLocale();
        return locale.getLanguage().equals("ru") ? "Главное меню удалено"
                : locale.getLanguage().equals("kg") ? "Негизги меню алынып салынды"
                : "Main menu has been deleted";
    }
}