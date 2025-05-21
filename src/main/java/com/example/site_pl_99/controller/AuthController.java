package com.example.site_pl_99.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @PostMapping("/login")
    public String login(
            @RequestParam String username,
           @RequestParam String password
    ) {
        return "success";
    }
}
