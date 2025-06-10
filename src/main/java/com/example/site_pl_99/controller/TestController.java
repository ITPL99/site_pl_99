package com.example.site_pl_99.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Тестовый контроллер, что бы показать как работать с
 * Нектарностью системы и распределения доступа по ролям в определенные
 * точки доступа системы
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * Точка доступа в которую открыт доступ для всех запросов
     * Даже для не авторизированных пользователей
     * @return
     */
    @GetMapping("/open-all")
    public String openAll() {
        throw new RuntimeException("ertyuilo;lkjhgfds");
//        return "открыт для всех";
    }

    /**
     * Точка доступа к которой разрешён доступ только для авторизированных пользователей
     * @return
     */
    @GetMapping("/open-auth")
    public String openAuth() {
        return "открыт для всех авторизированных";
    }

    /**
     Доступ только для администратора системы
     */
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/open-admin")
    public String openAdmin() {
        return "открыт для Admin";
    }

    /**
     * Доступ только для пользователей с ролью пользователь
     * Даже Админ не имеет доступа к данной точке доступа
     * @return
     */
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/open-user")
    public String openUser() {
        return "открыт для User";
    }

    /**
     * Точка доступа только для пользователя с ролью Гость
     * @return
     */
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @GetMapping("/open-guest")
    public String openDev() {
        return "открыт для guest";
    }

    /**
     * Точка доступа к которой разрешён доступ с ролями Админ и Пользователь
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/open-admin-user")
    public String openAdminUser() {
        return "открыт для admin-user";
    }

    /**
     * Точка доступа к которой разрешён доступ с ролями Гость и Пользователь
     * @return
     */
    @PreAuthorize("hasAnyAuthority('GUEST', 'USER')")
    @GetMapping("/open-guest-user")
    public String openGuestUser() {

        return "открыт для guest-user";
    }

    /**
     * Точка доступа к которой разрешён доступ с ролями Админ и Гость
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','GUEST')")
    @GetMapping("/open-admin-guest")
    public String openAdminGuest() {
        return "открыт для admin-guest";
    }


    /**
     * Spring Security берет данные роли из базы данных
     */
}
