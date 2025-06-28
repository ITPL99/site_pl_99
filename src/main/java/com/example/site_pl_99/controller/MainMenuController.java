package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MainMenuDtoRequest;
import com.example.site_pl_99.dto.MainMenuDtoResponse;
import com.example.site_pl_99.mapper.MainMenuMapper;
import com.example.site_pl_99.service.MainMenuService;
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

    @PostMapping("/add-main-menu")
    public ResponseEntity<MainMenuDtoResponse> addMainMenu(@RequestBody MainMenuDtoRequest mainMenuDtoRequest) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(mainMenuService.addMainMenu(MainMenuMapper.toMainMenuEntity(mainMenuDtoRequest))));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MainMenuDtoResponse> getMainMenuById(@PathVariable Long id) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(mainMenuService.getMainMenuById(id)));
    }

    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<MainMenuDtoResponse> updateMainMenu(@RequestBody MainMenuDtoRequest mainMenuDtoRequest, @PathVariable Long id) {
        return ResponseEntity.ok(MainMenuMapper.toMainMenuDtoResponse(mainMenuService.updateMainMenuById(id, MainMenuMapper.toMainMenuEntity(mainMenuDtoRequest))));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public String deleteMainMenuById(@PathVariable Long id) {
        mainMenuService.deleteMainMenuById(id);
        Locale locale = LocaleContextHolder.getLocale();
        return locale.getLanguage().equals("ru")? "Главное меню удалено":
                locale.getLanguage().equals("kg")? "Негизги меню алынып салынды":
                        "Main menu has been deleted";
    }
}
