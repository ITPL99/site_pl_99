package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MainMenuDtoRequest;
import com.example.site_pl_99.dto.MainMenuDtoResponse;
import com.example.site_pl_99.mapper.MainMenuMapper;
import com.example.site_pl_99.service.MainMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Контроллер для управления главным меню.
 * <p>
 * Предоставляет API для создания и получения элементов главного меню.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@RestController
@RequestMapping("/api/main-menu")
@SecurityRequirement(name = "bearerAuth")
@Tag(
        name = "Главное меню",
        description = """
                API для управления элементами главного меню.
                
                **Возможности:**
                - Добавление новых элементов меню
                - Получение элементов меню по ID
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
public class MainMenuController {

    private final MainMenuService mainMenuService;

    @Autowired
    public MainMenuController(MainMenuService mainMenuService) {
        this.mainMenuService = mainMenuService;
    }

    @Operation(
            summary = "Добавить элемент главного меню",
            description = """
                    Создает и добавляет новый элемент в главное меню.
                    
                    **Требуемая роль:** ADMIN
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Элемент главного меню успешно добавлен",
                    content = @Content(schema = @Schema(implementation = MainMenuDtoResponse.class))
            ),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping("/add-main-menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MainMenuDtoResponse> addMainMenu(
            @RequestBody(
                    description = "Данные элемента главного меню для добавления",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MainMenuDtoRequest.class))
            )
            @org.springframework.web.bind.annotation.RequestBody MainMenuDtoRequest mainMenuDtoRequest) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(
                mainMenuService.addMainMenu(MainMenuMapper.toMainMenuEntity(mainMenuDtoRequest))));
    }

    @Operation(
            summary = "Получить элемент главного меню по ID",
            description = "Возвращает информацию об элементе главного меню по его ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Элемент главного меню найден",
                    content = @Content(schema = @Schema(implementation = MainMenuDtoResponse.class))
            ),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "404", description = "Элемент не найден")
    })
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MainMenuDtoResponse> getMainMenuById(
            @Parameter(
                    description = "ID элемента главного меню",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(mainMenuService.getMainMenuById(id)));
    }

    @Operation(
            summary = "Обновить элемент главного меню по ID",
            description = """
                    Обновляет данные элемента главного меню.
                    
                    **Требуемая роль:** ADMIN
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Главное меню успешно обновлено",
                    content = @Content(schema = @Schema(implementation = MainMenuDtoResponse.class))
            ),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
            @ApiResponse(responseCode = "404", description = "Элемент не найден")
    })
    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<MainMenuDtoResponse> updateMainMenu(
            @RequestBody(
                    description = "Новые данные главного меню",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MainMenuDtoRequest.class))
            )
            @org.springframework.web.bind.annotation.RequestBody MainMenuDtoRequest mainMenuDtoRequest,
            @Parameter(
                    description = "ID элемента главного меню",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(
                mainMenuService.updateMainMenuById(id, MainMenuMapper.toMainMenuEntity(mainMenuDtoRequest))));
    }

    @Operation(
            summary = "Удалить элемент главного меню по ID",
            description = """
                    Удаляет элемент главного меню по его ID.
                    
                    **Требуемая роль:** ADMIN
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Главное меню успешно удалено"),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
            @ApiResponse(responseCode = "404", description = "Элемент не найден")
    })
    @DeleteMapping("/delete-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMainMenuById(
            @Parameter(
                    description = "ID элемента для удаления",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {
        mainMenuService.deleteMainMenuById(id);
        Locale locale = LocaleContextHolder.getLocale();
        return locale.getLanguage().equals("ru") ? "Главное меню удалено"
                : locale.getLanguage().equals("kg") ? "Негизги меню алынып салынды"
                : "Main menu has been deleted";
    }
}