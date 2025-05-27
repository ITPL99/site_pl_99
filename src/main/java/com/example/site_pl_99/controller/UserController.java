package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final Internalization internalization;

    public UserController(UserService userService, Internalization internalization) {
        this.userService = userService;
        this.internalization = internalization;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader(name = "Accept-Language", required = false)Locale language) {
        try{
            return ResponseEntity.ok(userService.getAll().stream().map(UserDtoResponse::new).toList());
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDtoRequestRegister userDtoRequestRegister, @RequestHeader(name = "Accept-Language", required = false)Locale language) {
        try{
            return ResponseEntity.ok(new UserDtoResponse( userService.save(userDtoRequestRegister)));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }

    @PostMapping("/get-login")
    public ResponseEntity<?> getUserByLogin(@RequestParam String username, @RequestHeader(name = "Accept-Language", required = false)Locale language) {
        try{
            return ResponseEntity.ok(new UserDtoResponse(userService.getByUsername(username)));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
        }
    }
}
